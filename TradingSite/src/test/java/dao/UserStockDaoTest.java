package dao;

import model.UserStock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class UserStockDaoTest {

    UserStockDao userStockDao;

    public UserStockDaoTest()
    {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        userStockDao = ctx.getBean("userStockDao", UserStockDao.class);
    }

    @Before
    public void setUp()
    {
        List<UserStock> stocks = userStockDao.getAllStocks();
        for (UserStock currentStock : stocks)
        {
            userStockDao.deleteStock(currentStock.getStockId());
        }
    }

    @Test
    public void testAddGetOwnedStock()
    {
        UserStock stock = PowerMockito.mock(UserStock.class);

        userStockDao.addStock(stock);

        UserStock retrievedStock = userStockDao.getStock(stock.getStockId());

        Assert.assertEquals(stock, retrievedStock);
    }

    @Test
    public void testDeleteOwnedStock()
    {
        UserStock stock = PowerMockito.mock(UserStock.class);
        userStockDao.addStock(stock);

        Assert.assertEquals(1, userStockDao.getAllStocks().size());

        userStockDao.deleteStock(stock.getStockId());

        Assert.assertEquals(0, userStockDao.getAllStocks().size());
    }

    @Test
    public void testEditOwnedStock()
    {
        UserStock stock = generateOwnedStockGeneric();
        userStockDao.addStock(stock);
        UserStock retrievedOriginalStock = userStockDao.getStock(stock.getStockId());

        Assert.assertEquals(stock, retrievedOriginalStock);

        stock.setOwnedUnits(10);
        userStockDao.editStock(stock);
        UserStock retrievedEditedStock = userStockDao.getStock(stock.getStockId());

        Assert.assertEquals(stock, retrievedEditedStock);
        Assert.assertNotEquals(retrievedOriginalStock, retrievedEditedStock);
    }

    @Test
    public void testGetAllStocks()
    {
        UserStock firstStock = generateOwnedStock(1, 1,"APPL");
        UserStock secondStock = generateOwnedStock(2, 1,"GNC");

        userStockDao.addStock(firstStock);
        userStockDao.addStock(secondStock);

        Assert.assertEquals(2, userStockDao.getAllStocks().size());
    }

    @Test
    public void testGetAllStocksByUser()
    {
        UserStock firstStock = generateOwnedStock(1, 1, "APPL");
        UserStock secondStock = generateOwnedStock(2, 2, "GNC");

        List<UserStock> stocksOfFirstUser = userStockDao.getStocksByUser(1);

        Assert.assertEquals(1, stocksOfFirstUser.size());
    }

    @Test
    public void testGetAllStocksByUserDescendingInPrice()
    {
        UserStock firstStock = generateOwnedStock(1, 1, "APPL");
        firstStock.setOwnedUnits(2);
        UserStock secondStock = generateOwnedStock(2, 1, "GNC");
        secondStock.setOwnedUnits(1);
        UserStock thirdStock = generateOwnedStock(3, 1, "IBM");
        thirdStock.setOwnedUnits(3);

        userStockDao.addStock(firstStock);
        userStockDao.addStock(secondStock);
        userStockDao.addStock(thirdStock);

        List<UserStock> stocks = userStockDao.getStocksByUserDescendingInPrice(1);

        List<UserStock> stocksSortedByPrice = userStockDao.getStocksByUser(1);
        Collections.sort(stocksSortedByPrice, new OwnedStockPriceComparator());

        Assert.assertEquals(stocks, stocksSortedByPrice);
    }

    private UserStock generateOwnedStockGeneric()
    {
        return generateOwnedStock(1, 1, "APPL");
    }

    private UserStock generateOwnedStock(int stockId, int userId, String symbol) {
        return new UserStock(symbol, new BigDecimal(2), 2, stockId, userId, 1);
    }

    private class OwnedStockPriceComparator implements Comparator<UserStock> {

        @Override
        public int compare(UserStock thisStock, UserStock otherStock) {
            BigDecimal thisStockValue = thisStock.getTotalValue();
            BigDecimal otherStockValue = otherStock.getTotalValue();

            return thisStockValue.compareTo(otherStockValue);
        }
    }

}