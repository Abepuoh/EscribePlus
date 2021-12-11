module controller {
	requires javafx.graphics;
	requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
	requires java.persistence;
	requires java.sql;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;

	opens model.DataObject to org.hibernate.commons.annotations, org.hibernate.orm.core, java.persistence;
    opens controller to javafx.graphics,javafx.base, org.hibernate.commons.annotations, org.hibernate.orm.core, javafx.fxml, java.persistence;
    opens utils to org.hibernate.commons.annotations, org.hibernate.orm.core, java.persistence;
    exports controller;
    exports model.DataObject;

}
