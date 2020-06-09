package com.pbinas.books.integrationTests;

import com.pbinas.books.config.TestConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
@WebAppConfiguration
public class BookListIntegrationTest extends AbstractIntegrationTest {
}
