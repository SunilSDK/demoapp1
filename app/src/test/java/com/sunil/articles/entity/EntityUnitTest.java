package com.sunil.articles.entity;

import com.sunil.articles.data.local.entity.RowsItem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class EntityUnitTest {

    @Test
    public void testId(){
        RowsItem articleEntity = new RowsItem();
        articleEntity.setId(10);
        assertEquals(articleEntity.getId(), 10);
    }

    @Test
    public void testTitle(){
        RowsItem articleEntity = new RowsItem();
        articleEntity.setTitle("test");
        assertEquals(articleEntity.getTitle(), "test");
    }

    @Test
    public void testContent(){
        RowsItem articleEntity = new RowsItem();
        articleEntity.setDescription("test");
        assertEquals(articleEntity.getDescription(), "test");
    }
    @Test
    public void testImageHref(){
        RowsItem articleEntity = new RowsItem();
        articleEntity.setImageHref("http://1.bp.blogspot.com/_VZVOmYVm68Q/SMkzZzkGXKI/AAAAAAAAADQ/U89miaCkcyo/s400/the_golden_compass_still.jpg");
        assertEquals(articleEntity.getImageHref(), "http://1.bp.blogspot.com/_VZVOmYVm68Q/SMkzZzkGXKI/AAAAAAAAADQ/U89miaCkcyo/s400/the_golden_compass_still.jpg");
    }


}
