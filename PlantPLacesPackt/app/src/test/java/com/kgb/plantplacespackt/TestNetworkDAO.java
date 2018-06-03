package com.kgb.plantplacespackt;

import com.kgb.dao.NetworkDAO;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

public class TestNetworkDAO {
    NetworkDAO networkDAO;

    @Before
    public void setup() {
        networkDAO = new NetworkDAO();
    }

    @Test
    public void fetchShouldSucceedWhenGivenValidURI() throws IOException {
        String result = networkDAO.fetch("http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=sdsds");
        System.out.println(result);
        assertEquals("{\"plants\":[]}-1", result);
    }
}
