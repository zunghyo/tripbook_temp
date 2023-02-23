package tripbook.tripbook.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "트립북 API 명세서",
                description = "API 명세서",
                version = "v1"
        )
)
@Configuration
public class SwaggerConfig {

        @Bean
        public GroupedOpenApi chatOpenApi() {
                String[] paths = {"/member/**"};

                return GroupedOpenApi.builder()
                        .group("MEMBER API v1")
                        .pathsToMatch(paths)
                        .build();
        }

}
