package wangzhongqiu.spring.dubbo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.LockSupport;

/**
 * @author wangzhongqiu
 */
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        LockSupport.park();
//        StdScheduler schedulerFactoryBean = (StdScheduler) context.getBean("schedulerFactoryBean");
//        //启动调度器
//        try {
//            schedulerFactoryBean.start();
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
    }
}
