module controller {
	
	requires javafx.graphics;
	requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
	requires java.persistence;
	requires java.sql;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;

    opens model.DAO to javafx.controls,javafx.graphics,javafx.base, org.hibernate.commons.annotations, org.hibernate.orm.core, javafx.fxml, java.persistence;
	opens model.DataObject to javafx.controls,javafx.graphics,javafx.base, org.hibernate.commons.annotations, org.hibernate.orm.core, javafx.fxml, java.persistence;
    opens controller to javafx.controls,javafx.base, org.hibernate.commons.annotations, org.hibernate.orm.core, javafx.fxml, java.persistence;
    opens utils to javafx.controls,javafx.graphics,javafx.base, org.hibernate.commons.annotations, org.hibernate.orm.core, javafx.fxml, java.persistence;
    exports controller;
    exports model.DataObject;
    exports utils;
    exports model.DAO;

}
