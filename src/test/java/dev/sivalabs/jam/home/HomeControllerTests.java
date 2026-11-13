package dev.sivalabs.jam.home;

import static org.assertj.core.api.Assertions.assertThat;

import dev.sivalabs.jam.BaseIT;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
class HomeControllerTests extends BaseIT {

    @Test
    void shouldShowLandingPageForAnonymousUser() {
        var result = mvc.get().uri("/");

        assertThat(result).hasStatusOk();
        assertThat(result).bodyText().contains("JAM Automation").contains("Sign in to get started");
    }

    @Test
    @WithUserDetails("siva@gmail.com")
    void shouldRedirectAuthenticatedUserToHome() {
        var result = mvc.get().uri("/");

        assertThat(result).hasStatus(HttpStatus.FOUND).hasRedirectedUrl("/home");
    }

    @Test
    @WithUserDetails("siva@gmail.com")
    void shouldRenderDashboardWithLeftNavAndUserName() {
        var result = mvc.get().uri("/home");

        assertThat(result).hasStatusOk();
        assertThat(result)
                .bodyText()
                .contains("Dashboard navigation")
                .contains("Welcome back")
                .contains("Siva");
    }

    @Test
    void shouldRequireLoginForHome() {
        var result = mvc.get().uri("/home");

        assertThat(result).hasStatus(HttpStatus.FOUND);
    }
}
