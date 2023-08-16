module dev.baumgaertner.contdownfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.baumgaertner.contdownfx to javafx.fxml;
    exports dev.baumgaertner.contdownfx;
}