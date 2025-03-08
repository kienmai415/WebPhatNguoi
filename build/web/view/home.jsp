<%-- 
    Document   : home
    Created on : Mar 8, 2025, 3:56:56 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Elegant Dashboard | Dashboard</title>
        <!-- Favicon -->
        <link rel="shortcut icon" href="./img/svg/logo.svg" type="image/x-icon">
        
        
        <!-- Custom styles -->       
        <link href="../css/styleVehicleOwner.min.css" rel="stylesheet" type="text/css"/>

        <!-- Link Bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        
        <!-- Link file CSS của bạn -->
        <link href="../css/styleVehicleOwner.css" rel="stylesheet" type="text/css"/>

        <!-- Link Bootstrap Icons -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    </head>
    <body>
        <div class="layer"></div>
        <!-- ! Body -->
        <a class="skip-link sr-only" href="#skip-target">Skip to content</a>
        <div class="page-flex">
            <!-- ! Sidebar -->
            <aside class="sidebar">
                <div class="sidebar-start">


                    <div class="sidebar-head">
                        <!--                        <a href="/" class="logo-wrapper" title="Home">
                                                    <span class="sr-only">Home</span>
                                                    <span class="icon logo" aria-hidden="true"></span>
                                                    <div class="logo-text">
                                                        <span class="logo-title">Elegant</span>
                                                        <span class="logo-subtitle">Dashboard</span>
                                                    </div>
                                                </a>-->
                        <button class="sidebar-toggle transparent-btn" title="Menu" type="button">
                            <span class="sr-only">Toggle menu</span>
                            <span class="icon menu-toggle" aria-hidden="true"></span>
                        </button>
                    </div>

                    <div class="sidebar-body">
                        <ul class="sidebar-body-menu">

                            <li>
                                <a href="#">
                                    <span class="" aria-hidden="true"></span>
                                    Sign Up For An Account
                                </a>
                            </li>
                            <ul class="cat-sub-menu">
                            </ul>

                            <li>
                                <a href="#">
                                    <span class="" aria-hidden="true"></span>
                                    Add Vehicle Information
                                </a>
                            </li>
                            <ul class="cat-sub-menu">
                            </ul>

                            <li>
                                <a href="#">
                                    <span class="" aria-hidden="true"></span>
                                    Schedule An Inspection
                                </a>
                            </li>
                            <ul class="cat-sub-menu">
                            </ul>

                            <li>
                                <a href="#">
                                    <span class="" aria-hidden="true"></span>
                                    Track Status And Test History
                                </a>
                            </li>
                            <ul class="cat-sub-menu">
                            </ul>
                        </ul>
                        <span class="system-menu__title">system</span>
                        <ul class="sidebar-body-menu">


                            <li>
                                <a href="##"><span class="icon setting" aria-hidden="true"></span>Settings</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </aside>
            <div class="main-wrapper"></div>
        </div>
        <!-- Chart library -->
        <script src="../plugins/chart.min.js" type="text/javascript"></script>
        <!-- Icons library -->
        <script src="../plugins/feather.min.js" type="text/javascript"></script>
        <!-- Custom scripts -->
        <script src="../js/script.js" type="text/javascript"></script>
        <!-- Link Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>

</html>
