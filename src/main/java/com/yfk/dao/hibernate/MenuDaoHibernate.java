package com.yfk.dao.hibernate;

import com.yfk.dao.MenuDao;
import com.yfk.model.Menu;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


/**
 * This class interacts with hibernate session to save/delete and
 * retrieve Role objects.
 *
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @author jgarcia (updated to hibernate 4)
 */
@Repository
public class MenuDaoHibernate extends GenericDaoHibernate<Menu, String> implements MenuDao {

    /**
     * Constructor to create a Generics-based version using Role as the entity
     */
    public MenuDaoHibernate() {
        super(Menu.class);
    }

}
