# Automatización Pragma


## Pre-requisitos de instalación del proyecto:
- entorno de desarrollo Intellij
- maven
- jdk version 11
- google chrome, mozilla firefox, microsoft edge


## Instalación y configuración del proyecto
1. En el entorno de desarrollo abrir la carpeta del proyecto previamente descargado
2. En la pestaña maven, seleccionar el icono de "Reload All Maven Projects"
3. Esperar que el proyecto termine de construir e instalar las dependencias

## Ejecución
Por medio de la clase Runner
1. Ingresar a la clase Runner ubicada en la carpeta src/test/runners
2. Modificar el parametro Tags con el tag del escenario a ejecutar
3. Al terminar la ejecucion ingresar a la pestaña maven, ingresar a plugins,
   luego desplegar el plugin de serenity y hacer doble click en serenity:aggregate
4. El reporte se podra visualizar en la ruta target/site/serenity/index.html

Por medio de la consola de comandos 
1. Desde la terminal de IntelliJ o la consola de comandos del sistema operativo,
ejecutar el siguiente comando: mvn clean verify -Dcucumber.filter.tags="@carritoCompra"
2. Al finalizar la ejecucion el reporte se visualizara en la ruta target/site/serenity/index.html


<div align="left">
  <img src="https://s3.amazonaws.com/accredible_temp_credential_images/1573460724648430.png" height="40" alt="serenity logo"  />
  <img src="https://static1.smartbear.co/cucumber/media/images/logos/icons/cucumber-open-icon.svg" height="40" alt="cucumber logo"  />
  <img src="https://upload.wikimedia.org/wikipedia/zh/8/88/Java_logo.png" height="50" alt="java logo"  />
  <img src="https://miro.medium.com/v2/resize:fit:640/format:webp/1*kbSGIVukG6lL7JtAa9wiDA.png" height="50" alt="maven logo"  />
  <img src="https://images.credly.com/size/680x680/images/af6eb254-169b-452a-a63c-5a1f986ed4cc/appium.png" height="50" alt="appium logo"  />
</div>








