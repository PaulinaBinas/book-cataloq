package com.pbinas.books.unitTests;

import com.pbinas.books.config.TestConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class BookUnitTest extends AbstractUnitTest {
}