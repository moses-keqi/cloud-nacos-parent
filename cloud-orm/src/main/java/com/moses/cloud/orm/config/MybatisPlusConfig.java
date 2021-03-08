package com.moses.cloud.orm.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author HanKeQi
 * @Date 2021/1/11 下午2:53
 * @Version 1.0
 **/
@Configuration
@MapperScan("com.moses.cloud.**.mapper")
@EnableTransactionManagement
public class MybatisPlusConfig {

    /**
     * 分页器
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
        //防止溢出设置分页返回最大值
        innerInterceptor.setMaxLimit(500L);
        //目前是默认跳转到第一页，希望能增加个参数可以指定跳转到最后一页或者第一页
        innerInterceptor.setOverflow(false);
        //乐观锁插件:当要更新一条记录的时候，希望这条记录没有被别人更新
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        interceptor.addInnerInterceptor(innerInterceptor);
        return interceptor;
    }
}
