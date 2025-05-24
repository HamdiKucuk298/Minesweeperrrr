module Minesweeperrrr {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	 exports application;//dosyayı dısarı cıkarttım ya onun ıcın
	
	opens application to javafx.fxml;//hata ıcın
}
