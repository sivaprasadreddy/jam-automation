package dev.sivalabs.jam.users;

import static org.assertj.core.api.Assertions.assertThat;

import dev.sivalabs.jam.BaseIT;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
class UserControllerTests extends BaseIT {

    @Test
    void shouldRenderLoginPage() {
        var result = mvc.get().uri("/login");

        assertThat(result).hasStatusOk();
        assertThat(result).bodyText().contains("Sign in").contains("name=\"_csrf\"");
    }

    @Test
    void shouldShowLoginErrorMessage() {
        var result = mvc.get().uri("/login?error");

        assertThat(result).hasStatusOk();
        assertThat(result).bodyText().contains("Invalid email or password.");
    }

    @Test
    void shouldShowLoggedOutMessage() {
        var result = mvc.get().uri("/login?logout");

        assertThat(result).hasStatusOk();
        assertThat(result).bodyText().contains("You have been logged out.");
    }
}
