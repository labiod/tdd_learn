package com.kgb.plantplacespackt;

import com.kgb.dao.BDDTestPlantDAO;
import com.kgb.dao.TestNetworkDAO;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Krzysztof Betlej <k.betlej@samsung.com>.
 * @date 6/6/18
 * @copyright Copyright (c) 2016 by Samsung Electronics Polska Sp. z o. o.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BDDTestPlantDAO.class,
        TestNetworkDAO.class
})
public class DAOTestSuite {
}
