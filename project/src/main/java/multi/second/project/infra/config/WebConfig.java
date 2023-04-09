package multi.second.project.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import multi.second.project.infra.interceptor.MemberAuthInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//registry.addInterceptor(new MemberAuthInterceptor()).addPathPatterns("/member/**");
		
	}

	// url: localhost8080:/gallery/** 에 접속했을 때에 파일에 접근
	// 나중에 화면단의 <img src=...> 에서 src 에 들어간다.
	private String connectPath = "/gallery/**";
	// 실제 이미지가 저장되어 있는 외부경로 (file:/// = C:/ 라고 생각하자)
	// 끝에 꼭 '/' 로 닫아줘야 한다.
	private String resourcePath = "file:///post/postImg";

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(connectPath)
				.addResourceLocations(resourcePath);
		
		registry.addResourceHandler("/profile/**")
				.addResourceLocations("file:///profile/profileImg");
	}
}
