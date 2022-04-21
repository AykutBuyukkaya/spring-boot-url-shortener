package com.buyukkaya.urlshortener.domain.common.model.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(MockitoJUnitRunner.class)
class ApiResponseTest {

    ApiResponse apiResponse;

    @BeforeEach
    void setUp() {

        apiResponse = ApiResponse.builder()
                .message("Testing!!")
                .data(new String("Testing123!"))
                .build();

    }

    @Test
    void getMessage() {

        assertThat(apiResponse.getMessage()).isEqualTo("Testing!!");

    }

    @Test
    void getData() {

        assertThat(apiResponse.getData()).isEqualTo("Testing123!");

    }

    @Test
    void setMessage() {

        apiResponse.setMessage("Test22");

        assertThat(apiResponse.getMessage()).isEqualTo("Test22");

    }

    @Test
    void setData() {

        apiResponse.setData(123);

        assertThat(apiResponse.getData()).isEqualTo(123);
    }

    @Test
    void testEquals() {

        ApiResponse result = ApiResponse.builder()
                .message("Testing!!")
                .data("Testing123!")
                .build();

        assertThat(apiResponse.equals(result)).isTrue();

    }

    @Test
    void canEqual() {

        ApiResponse result = ApiResponse.builder()
                .build();

        assertThat(apiResponse.canEqual(result)).isTrue();

    }

    @Test
    void testHashCode() {

        ApiResponse response = ApiResponse.builder()
                .message("Testing!!")
                .data("Testing123!")
                .build();


        assertThat(response.hashCode()).isEqualTo(apiResponse.hashCode());

    }

    @Test
    void testToString() {

        assertThat(apiResponse.toString()).hasToString("ApiResponse(message=Testing!!, data=Testing123!)");


    }

    @Test
    void builder() {

        assertThat(apiResponse.getMessage()).isEqualTo("Testing!!");
        assertThat(apiResponse.getData()).isEqualTo("Testing123!");

    }
}