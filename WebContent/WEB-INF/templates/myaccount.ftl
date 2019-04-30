<!DOCTYPE html>
<!--  This site was created in Webflow. http://www.webflow.com  -->
<!--  Last Published: Thu Apr 12 2018 13:33:36 GMT+0000 (UTC)  -->
<html data-wf-page="5aca7dd9af4ae86fec30fd78" data-wf-site="5aa6bf1441b9c7b66ebd3a3b">
<head>
  <meta charset="utf-8">
  <title>MyAccount</title>
  <meta content="MyAccount" property="og:title">
  <meta content="width=device-width, initial-scale=1" name="viewport">
  <meta content="Webflow" name="generator">
  <link href="css/normalize.css" rel="stylesheet" type="text/css">
  <link href="css/webflow.css" rel="stylesheet" type="text/css">
  <link href="css/moviewebsite-8866eb.webflow.css" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/webfont/1.4.7/webfont.js" type="text/javascript"></script>
  <script type="text/javascript">WebFont.load({  google: {    families: ["Roboto:regular","Roboto:100,300,regular"]  }});</script>
  <!-- [if lt IE 9]><script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js" type="text/javascript"></script><![endif] -->
  <script type="text/javascript">!function(o,c){var n=c.documentElement,t=" w-mod-";n.className+=t+"js",("ontouchstart"in o||o.DocumentTouch&&c instanceof DocumentTouch)&&(n.className+=t+"touch")}(window,document);</script>
  <link href="https://daks2k3a4ib2z.cloudfront.net/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
  <link href="https://daks2k3a4ib2z.cloudfront.net/img/webclip.png" rel="apple-touch-icon">
</head>
<body class="body-5">
  <div data-collapse="medium" data-animation="default" data-duration="400" class="navbar-5 w-nav">
    <div class="container-9 w-container">
      <div class="w-nav-button">
        <div class="w-icon-nav-menu"></div>
      </div>
      <div class="container-8 w-container"><a href="index.html" class="w-nav-brand"><img src="images/bganimations.png" width="250.5" class="image-4"></a>
        <nav role="navigation" class="w-clearfix w-nav-menu"><a href="index.html" class="nav-link w-nav-link">Home</a><a href="library.html" class="nav-link w-nav-link">Library</a><a href="movie.html" class="nav-link w-nav-link">Movies</a><a href="myaccount.html" class="nav-link w-nav-link">My Account</a>
          <div class="form-block-2 w-clearfix w-form">
            <form id="email-form" name="email-form" data-name="Email Form" action="Servlet" method="get" class="form-3 w-clearfix"><input type="submit" name="submit" value="Log Out" data-wait="Please wait..." class="submit-button-7 w-button"></form>
            <div class="w-form-done">
              <div>Thank you! Your submission has been received!</div>
            </div>
            <div class="w-form-fail">
              <div>Oops! Something went wrong while submitting the form.</div>
            </div>
          </div>
        </nav>
        <div class="w-nav-button">
          <div class="w-icon-nav-menu"></div>
        </div>
      </div>
    </div>
  </div>
  <div>
    <h1 class="heading-4">Hey ${NAME}!</h1>
    <p class="heading-4">Here you can update your profile</p>
  </div>
  <div class="heading-4 w-form">
    <form id="personalInformationForm" name="email-form" data-name="Email Form" action="Servlet" method="get" class="form">
      <div class="section-12"><img src="images/Asset_14x.png" width="32">
        <p class="paragraph-8"><strong class="bold-text">Personal Information</strong></p><label for="firstName" class="field-label-3">First Name</label><input type="text" class="w-input" maxlength="256" name="firstName" data-name="firstName" placeholder="Enter your first name" id="firstName" required=""><label for="lastName" class="field-label-3">Last Name</label><input type="text" class="w-input" maxlength="256" name="lastName" data-name="lastName" placeholder="Enter your last name" id="lastName" required=""></div>
    </form>
    <div class="w-form-done">
      <div>Thank you! Your submission has been received!</div>
    </div>
    <div class="w-form-fail">
      <div>Oops! Something went wrong while submitting the form.</div>
    </div>
  </div>
  <div class="heading-4 w-form">
    <form id="accountForm" name="email-form" data-name="Email Form" action="http://Servlet" class="form">
      <div class="section-12"><img src="images/Asset_24x.png" width="32">
        <p class="paragraph-8"><strong class="bold-text">Account Information</strong></p>
        <div class="row-2 w-row">
          <div class="w-col w-col-6"><label for="password" class="field-label-4">Password</label><input type="password" class="w-input" maxlength="256" name="password" data-name="password" placeholder="Enter your password" id="password" required=""></div>
          <div class="w-col w-col-6"><label for="confirmPassword" class="field-label-2">Confirm Password:</label><input type="password" class="text-field w-input" maxlength="256" name="confirmPassword" data-name="confirmPassword" placeholder="Confirm your password" id="confirmPassword" required=""></div>
        </div><label for="creditCard">Credit Card</label><input type="text" class="w-input" maxlength="256" name="creditCard" data-name="creditCard" placeholder="Enter your credit card information" id="creditCard" required=""></div>
    </form>
    <div class="w-form-done">
      <div>Thank you! Your submission has been received!</div>
    </div>
    <div class="w-form-fail">
      <div>Oops! Something went wrong while submitting the form.</div>
    </div>
  </div>
  <div class="heading-4 w-form">
    <form id="accountForm" name="email-form" data-name="Email Form" action="http://Servlet" class="form"><img src="images/Asset_34x.png" width="32">
      <p class="paragraph-8"><strong class="bold-text">Billing Information</strong></p>
      <div class="section-12">
        <p class="paragraph-8"><strong class="bold-text">Street Address<br></strong></p><input type="text" class="w-input" maxlength="256" name="streetAddress" data-name="streetAddress" placeholder="Enter your street address" id="streetAddress-2" required=""><label for="city">City</label><input type="text" class="w-input" maxlength="256" name="city" data-name="city" placeholder="Enter your city" id="city" required="">
        <div class="row-2 w-row">
          <div class="w-col w-col-6"><label for="state">State</label><input type="text" class="w-input" maxlength="256" name="state" data-name="state" placeholder="Enter your state" id="state" required=""></div>
          <div class="w-col w-col-6"><label for="zipCode-2" class="field-label-2">Zip Code</label><input type="text" class="text-field-2 w-input" maxlength="256" name="zipCode" data-name="zipCode" placeholder="Enter your zip code" id="zipCode-2" required=""></div>
        </div><input type="submit" value="Update Profile" data-wait="Please wait..." class="submit-button-3 w-button"></div>
    </form>
    <div class="w-form-done">
      <div>Thank you! Your submission has been received!</div>
    </div>
    <div class="w-form-fail">
      <div>Oops! Something went wrong while submitting the form.</div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.3.1.min.js" type="text/javascript" intergrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
  <script src="js/webflow.js" type="text/javascript"></script>
  <!-- [if lte IE 9]><script src="https://cdnjs.cloudflare.com/ajax/libs/placeholders/3.0.2/placeholders.min.js"></script><![endif] -->
</body>
</html>