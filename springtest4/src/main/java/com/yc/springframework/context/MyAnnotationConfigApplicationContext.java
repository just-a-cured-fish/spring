package com.yc.springframework.context;


import com.yc.springframework.stereotype.*;


import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class MyAnnotationConfigApplicationContext implements MyApplicationContext{
    public MyAnnotationConfigApplicationContext(Class<?>... componentClasses)  {
      try{

          register(componentClasses);
      }catch (Exception e){
          e.printStackTrace();
      }


    }

    private void register(Class<?>[] componentClasses) throws IllegalAccessException, InstantiationException, InvocationTargetException, IOException, ClassNotFoundException {
        if(componentClasses==null||componentClasses.length<=0){
            throw new RuntimeException("没有指定配置类");
        }
        for(Class cl:componentClasses){
            if(!cl.isAnnotationPresent(MyConfiguration.class)){
                    continue;
            }
            String[] basePackages=getAppConfigBasePackages(cl);
            if(cl.isAnnotationPresent(MyConfiguration.class)){
                MyCompenentScan mcs=(MyCompenentScan)cl.getAnnotation(MyCompenentScan.class);
                if(mcs.basePackages()!=null&&mcs.basePackages().length>0){
                    basePackages=mcs.basePackages();
                }

            }
            //处理@MyBean的情况
            Object obj=cl.newInstance();//obj就是当前的 MYAPPconfig对象
            handleAtMyBean(cl,obj);
            //处理basePackages基础包下所有的托管bean
            for(String basePackage:basePackages){
                scanPackagesAndSubPackgeClasses(basePackage);
            }
            handManagedBean();
            handleDI(beanMap);

        }



    }

    private void handleDI(Map<String, Object> beanMap) throws InvocationTargetException, IllegalAccessException {
        Collection<Object> objectCollection=beanMap.values();
        for(Object obj:objectCollection){
            Class cls=obj.getClass();
            Method[] ms=cls.getDeclaredMethods();
            for(Method m:ms){
                if(m.isAnnotationPresent(MyAutowired.class)&&m.getName().startsWith("set")){
                            invokeAutowiredMethod(m,obj);
                }else if(m.isAnnotationPresent(MyResource.class)){
                             invokeResourceMethod(m,obj);
                }
            }
            Field[] fs=cls.getDeclaredFields();
            for(Field field:fs){
                if(field.isAnnotationPresent(MyAutowired.class)){

                }else if(field.isAnnotationPresent(MyResource.class)){

                }
            }


        }
    }

    private void invokeResourceMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException {
        MyResource mr=m.getAnnotation(MyResource.class);
        String beanId=mr.name();
        if(beanId==null||beanId.equalsIgnoreCase("")){
            String pname=m.getParameterTypes()[0].getSimpleName();
            beanId=pname.substring(0,1).toLowerCase()+pname.substring(1);
        }

        Object o=beanMap.get(beanId);
        m.invoke(obj,o);



    }
    private void invokeAutowiredMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException {
           Class typeClass=m.getParameterTypes()[0];
           Set<String> keys=beanMap.keySet();
           for(String key:keys){
               Object o=beanMap.get(key);
               Class[] interfaces=o.getClass().getInterfaces();
               for(Class c:interfaces){
                   if(c==typeClass){
                       m.invoke(obj,o);
                       break;
                   }
               }
           }



    }

    /*
     处理managedBeanClasses所有的class类，筛选出所有的@Component@Service,@Repository的类，并实例化
     */
    private void handManagedBean() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        for(Class c:managedBeanClasses){

            if(c.isAnnotationPresent(MyComponent.class)){
                saveManagedBean(c);
            }else  if(c.isAnnotationPresent(MyService.class)){
                saveManagedBean(c);
            }else  if(c.isAnnotationPresent(MyRepository.class)){
                saveManagedBean(c);
            }else  if(c.isAnnotationPresent(MyController.class)){
                saveManagedBean(c);
            }else{
                continue;
            }
        }


    }
    private void saveManagedBean(Class c) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object o=c.newInstance();
        handlePostConstruct(o,c);
        String beanId=c.getSimpleName().substring(0,1).toLowerCase()+c.getSimpleName().substring(1);
        beanMap.put(beanId,o);
    }

    private void scanPackagesAndSubPackgeClasses(String basePackage) throws IOException, ClassNotFoundException {
        String packagePath=basePackage.replaceAll("\\.","/");
        System.out.println("扫描包路径："+basePackage+",替换后："+packagePath);
        Enumeration<URL> files=Thread.currentThread().getContextClassLoader().getResources(packagePath);
        while(files.hasMoreElements()){
            URL url=files.nextElement();
            System.out.println("配置的扫描路径为"+url.getFile());
            findClassesInPackages(url.getFile(),basePackage);//第二个参数:com.yc.bean

        }

    }
    private Set<Class> managedBeanClasses=new HashSet<>();
    /**
 * 查找file 下面及子包所有的要托管的class,存到一个set
 */
    private void findClassesInPackages(String file, String basePackage) throws ClassNotFoundException {
        File f=new File(file);

        File[] classFiles=f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".class")||file.isDirectory();
            }
        });
        for(File cf:classFiles){
            if(cf.isDirectory()){
              //  basePackage+="."+cf.getName().substring(cf.getName().lastIndexOf("/")+1);
                findClassesInPackages(cf.getAbsolutePath(),basePackage+"."+cf.getName().substring(cf.getName().lastIndexOf("/")+1));
            }else{
                URL[] urls=new URL[]{};
                URLClassLoader ucl=new URLClassLoader(urls);
                Class c=ucl.loadClass(basePackage+"."+cf.getName().replace(".class",""));
                managedBeanClasses.add(c);
            }

        }

    }

    /**
     *处理MyAppConfig配置类中的@Bean注解，完成IOC操作
     * @param cls
     * @param obj
     */
    private void handleAtMyBean(Class cls, Object obj) throws InvocationTargetException, IllegalAccessException {
        Method[] ms= cls.getDeclaredMethods();
        //判断哪个方法上有@MyBean注解
        for(Method m:ms){

            if(m.isAnnotationPresent(MyBean.class)){
                System.out.println(m);
                Object o=m.invoke(obj);
                //去处理返回对象中@MyPostConstruct对应的方法
                handlePostConstruct(o,o.getClass());
                beanMap.put(m.getName(),o);
            }
        }


    }

    private void handlePostConstruct(Object o, Class<?> cls) throws InvocationTargetException, IllegalAccessException {
            Method[] ms=cls.getDeclaredMethods();
            for(Method m:ms){
                if(m.isAnnotationPresent(MyPostConstruct.class)){
                    m.invoke(o);
                }
            }

    }

    //获取类的基础包路径
    private String[] getAppConfigBasePackages(Class cl) {
        String[] paths=new String[1];
        paths[0]=cl.getPackage().getName();
        return paths;
    }

    Map<String,Object> beanMap=new HashMap<>();


    public Object getBean(String id) throws RuntimeException {
        return beanMap.get(id);
    }
}