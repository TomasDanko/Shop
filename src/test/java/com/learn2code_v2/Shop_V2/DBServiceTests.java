//package com.learn2code_v2.Shop_V2;
//
//import com.learn2code_v2.Shop_V2.db.service.api.CustomerService;
//import com.learn2code_v2.Shop_V2.db.service.api.MerchantService;
//import com.learn2code_v2.Shop_V2.db.service.api.ProductService;
//import com.learn2code_v2.Shop_V2.domain.Customer;
//import com.learn2code_v2.Shop_V2.domain.Merchant;
//import com.learn2code_v2.Shop_V2.domain.Product;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
////@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
//public class DBServiceTests {
//
//    @Autowired
//    private CustomerService customerService;
//
//    @Autowired
//    private MerchantService merchantService;
//
//    @Autowired
//    private ProductService productService;
//
//    private Merchant merchant;
//
//    @Before
//    public void createMerchant(){
//        if(merchant == null){
//            merchant = new Merchant("Tomas", "email@email", "address");
//            Integer id = merchantService.add(merchant);
//            assert id != null;
//            merchant.setId(id);
//        }
//
//    }
//
//    @Test
//    public void customer(){
//        Customer customer = new Customer("Ferko", "Mrkvicka", "testemail", "addressa", 20, "0907");
//        Integer id = customerService.addCustomer(customer);
//        assert id != null;
//        customer.setId(id);
//
//        Customer customerFromDb = customerService.get(id);
//        Assert.assertEquals(customer, customerFromDb);
//
//        List<Customer> customers = customerService.getCustomers();
//        Assert.assertEquals(1, customers.size());
//        Assert.assertEquals(customer, customers.get(0));
//
//    }
//
//    @Test
//    public void merchant(){
////        Merchant merchant = new Merchant("Tomas", "email@email", "address");
////        Integer id = merchantService.add(merchant);
////        assert id != null;
////        merchant.setId(id);
//
//        Merchant fromDb = merchantService.get(merchant.getId());
//        Assert.assertEquals(merchant, fromDb);
//
//        List<Merchant> merchants = merchantService.getMerchants();
//        Assert.assertEquals(1, merchants.size());
//        Assert.assertEquals(merchant, merchants.get(0));
//    }
//
//
//    @Test
//    public void Product(){
//        Product product = new Product(merchant.getId(), "name", "desc", 5, 1);
//        Integer id = productService.add(product);
//        assert id != null;
//        product.setId(id);
//
//        Product fromDb = productService.get(id);
//        Assert.assertEquals(product, fromDb);
//
//    }
//}
