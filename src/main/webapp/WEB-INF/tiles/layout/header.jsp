<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<div class="header">
  <!-- Top Bar -->
  <div class="top-navbar">
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <span class="aux-text d-none d-md-inline-block">
            <ul class="inline-links inline-links--style-1">
              <li class="d-none d-lg-inline-block">
                CryptoGraph - Compare and Share                        
              </li>
              <li>
                <i class="fa fa-envelope"></i>
                <a href="#">support@cryptograph.com</a>
              </li>
            </ul>
          </span>
        </div>
      </div>
    </div>
  </div>
  <!-- Navbar -->
  <nav class="navbar navbar-expand-lg navbar--bold navbar-light bg-default ">
    <div class="container navbar-container">
      <!-- Brand/Logo -->
      <a class="navbar-brand" href="../../index.html">
      <img src="../${contextRoot}/assets/images/logo/logo-1-b.png" class="" alt="Boomerang">
      </a>
      <div class="d-inline-block">
        <!-- Navbar toggler  -->
        <button class="navbar-toggler hamburger hamburger-js hamburger--spring" type="button" data-toggle="collapse" data-target="#navbar_main" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="hamburger-box">
        <span class="hamburger-inner"></span>
        </span>
        </button>
      </div>
      <div class="collapse navbar-collapse align-items-center justify-content-end" id="navbar_main">
        <!-- Navbar links -->
        <ul class="navbar-nav">
          <li class="nav-item dropdown megamenu">
            <a class="nav-link" href="../../index.html">
            Home
            </a>
          </li>
          <li class="nav-item">
            <a href="../../documentation/getting-started/introduction.html" class="nav-link">
            User opinions
            </a>
          </li>
        </ul>
        <ul class="navbar-nav ml-lg-auto">
          <li class="nav-item">
            <a href="../../documentation/getting-started/introduction.html" class="nav-link">
            Login
            </a>
          </li>
          <li class="nav-item">
            <a href="../../documentation/getting-started/introduction.html" class="nav-link">
            Register
            </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</div>