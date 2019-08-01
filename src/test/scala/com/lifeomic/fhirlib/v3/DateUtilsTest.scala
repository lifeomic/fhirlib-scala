package com.lifeomic.fhirlib.v3

import java.time.LocalDateTime

import org.junit.{Assert, Test}

class DateUtilsTest {

    @Test
    def testParseLocalDateTime_8GroupMatch(): Unit = {
        val localDateTime = DateUtils.parseLocalDateTime("2018-01-03T07:01:01.123Z")
        Assert.assertEquals(LocalDateTime.of(2018, 1, 3, 7, 1, 1, 123000000), localDateTime.get)
    }

    @Test
    def testParseLocalDateTime_8GroupMatchLocalTz(): Unit = {
        val localDateTime = DateUtils.parseLocalDateTime("2018-01-03T07:01:01.123-04:00")
        Assert.assertEquals(LocalDateTime.of(2018, 1, 3, 11, 1, 1, 123000000), localDateTime.get)
    }

    @Test
    def testParseLocalDateTime_7GroupMatch(): Unit = {
        val localDateTime = DateUtils.parseLocalDateTime("2018-01-03T07:01:01Z")
        Assert.assertEquals(LocalDateTime.of(2018, 1, 3, 7, 1, 1, 0), localDateTime.get)
    }

    @Test
    def testParseLocalDateTime_7GroupMatchLocalTz(): Unit = {
        val localDateTime = DateUtils.parseLocalDateTime("2018-01-03T07:01:01-04:00")
        Assert.assertEquals(LocalDateTime.of(2018, 1, 3, 11, 1, 1, 0), localDateTime.get)
    }

    @Test
    def testParseLocalDateTime_7GroupMatchOnMillis(): Unit = {
        val localDateTime = DateUtils.parseLocalDateTime("2018-01-03T07:01:01.123")
        Assert.assertEquals(LocalDateTime.of(2018, 1, 3, 7, 1, 1, 123000000), localDateTime.get)
    }

    @Test
    def testParseLocalDateTime_6GroupMatch(): Unit = {
        val localDateTime = DateUtils.parseLocalDateTime("2018-01-03T07:01:01")
        Assert.assertEquals(LocalDateTime.of(2018, 1, 3, 7, 1, 1, 0), localDateTime.get)
    }

    @Test
    def testParseLocalDateTime_6GroupMatchWithTz(): Unit = {
        val localDateTime = DateUtils.parseLocalDateTime("2018-01-03T07:01:01Z")
        Assert.assertEquals(LocalDateTime.of(2018, 1, 3, 7, 1, 1, 0), localDateTime.get)
    }

    @Test
    def testParseLocalDateTime_6GroupMatchWithLocalTz(): Unit = {
        val localDateTime = DateUtils.parseLocalDateTime("2018-01-03T07:01:01-04:00")
        Assert.assertEquals(LocalDateTime.of(2018, 1, 3, 11, 1, 1, 0), localDateTime.get)
    }

    @Test
    def testParseLocalDateTime_5GroupMatch(): Unit = {
        val localDateTime = DateUtils.parseLocalDateTime("2018-01-03T07:01")
        Assert.assertEquals(LocalDateTime.of(2018, 1, 3, 7, 1, 0, 0), localDateTime.get)
    }

    @Test
    def testParseLocalDateTime_4GroupMatch(): Unit = {
        val localDateTime = DateUtils.parseLocalDateTime("2018-01-03T07")
        Assert.assertEquals(LocalDateTime.of(2018, 1, 3, 7, 0, 0, 0), localDateTime.get)
    }

    @Test
    def testParseLocalDateTime_3GroupMatch(): Unit = {
        val localDateTime = DateUtils.parseLocalDateTime("2018-01-03")
        Assert.assertEquals(LocalDateTime.of(2018, 1, 3, 0, 0, 0, 0), localDateTime.get)
    }

    @Test
    def testParseLocalDateTime_2GroupMatch(): Unit = {
        val localDateTime = DateUtils.parseLocalDateTime("2018-01")
        Assert.assertEquals(LocalDateTime.of(2018, 1, 1, 0, 0, 0, 0), localDateTime.get)
    }

    @Test
    def testParseLocalDateTime_1GroupMatch(): Unit = {
        val localDateTime = DateUtils.parseLocalDateTime("2018")
        Assert.assertEquals(LocalDateTime.of(2018, 1, 1, 0, 0, 0, 0), localDateTime.get)
    }
}
