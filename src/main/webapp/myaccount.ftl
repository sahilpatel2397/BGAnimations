<!DOCTYPE html>
<!--  This site was created in Webflow. http://www.webflow.com  -->
<!--  Last Published: Sun Apr 08 2018 22:13:55 GMT+0000 (UTC)  -->
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
<body>
  <div data-collapse="medium" data-animation="default" data-duration="400" class="w-nav">
    <div class="w-container">
      <div class="w-container"><a href="#" class="w-nav-brand"><img src="images/bganimations.png" width="279" class="image-4"></a>
        <nav role="navigation" class="w-nav-menu"><a href="#" class="nav-link w-nav-link">Home</a><a href="#" class="nav-link w-nav-link">Movies</a><a href="login.html" class="nav-link w-nav-link">Registration</a><a href="loginpage.html" class="nav-link w-nav-link">Login</a><a href="myaccount.html" class="nav-link w-nav-link">My Account</a></nav>
        <div class="w-nav-button">
          <div class="w-icon-nav-menu"></div>
        </div>
      </div>
      <div class="w-nav-button">
        <div class="w-icon-nav-menu"></div>
      </div>
    </div>
  </div>
  <div>
    <h1 class="heading-4">Hey Danny!</h1>
    <p class="heading-4">Here you can update your profile</p>
  </div>
  <div class="heading-4 w-form"><img src="images/Asset_14x.png" width="32">
    <p class="paragraph-8"><strong class="bold-text">Personal Information</strong></p>
    <form id="personalInformationForm" name="email-form" data-name="Email Form" class="form"><label for="name-2" class="field-label-3">Name</label><input type="text" class="w-input" maxlength="256" name="name" data-name="Name" placeholder="Enter your name" id="accountName" required=""><label for="email">Email:</label><input type="email" class="w-input" maxlength="256" name="email" data-name="Email" placeholder="Enter your email" id="accountEmail" required=""></form>
    <div class="w-form-done">
      <div>Thank you! Your submission has been received!</div>
    </div>
    <div class="w-form-fail">
      <div>Oops! Something went wrong while submitting the form.</div>
    </div>
  </div>
  <div class="heading-4 w-form"><img src="images/Asset_24x.png" width="32">
    <p class="paragraph-8"><strong class="bold-text">Account Information</strong></p>
    <form id="accountForm" name="email-form" data-name="Email Form" class="form">
      <div class="row-2 w-row">
        <div class="w-col w-col-6"><label for="name-2" class="field-label-4">Password</label><input type="password" class="w-input" maxlength="256" name="name-2" data-name="Name 2" placeholder="Enter your password" id="accountPassword" required=""></div>
        <div class="w-col w-col-6"><label for="email-2" class="field-label-2">Confirm Password:</label><input type="password" class="text-field w-input" maxlength="256" name="email-2" data-name="Email 2" placeholder="Confirm your password" id="accountConfirmPassword" required=""></div>
      </div><label for="email">Credit Card</label><input type="text" class="w-input" maxlength="256" name="email-2" data-name="Email 2" placeholder="Enter your credit card information" id="accountCreditCard" required=""></form>
    <div class="w-form-done">
      <div>Thank you! Your submission has been received!</div>
    </div>
    <div class="w-form-fail">
      <div>Oops! Something went wrong while submitting the form.</div>
    </div>
  </div>
  <div class="heading-4 w-form"><img src="images/Asset_34x.png" width="32">
    <p class="paragraph-8"><strong class="bold-text">Billing Address<br></strong></p>
    <form id="accountForm" name="email-form" data-name="Email Form" class="form"><label for="name" class="field-label-5">Street Name</label><input type="text" class="w-input" maxlength="256" name="name-2" data-name="Name 2" placeholder="Enter your street address" id="accountStreetName" required=""><label for="email">City</label><input type="text" class="w-input" maxlength="256" name="email-2" data-name="Email 2" placeholder="Enter your city" id="accountCity" required="">
      <div class="row-2 w-row">
        <div class="w-col w-col-6"><label for="email">State</label><input type="text" class="w-input" maxlength="256" name="email-2" data-name="Email 2" placeholder="Enter your state" id="accountState" required=""></div>
        <div class="w-col w-col-6"><label for="email" class="field-label-2">Zip Code</label><input type="text" class="text-field-2 w-input" maxlength="256" name="email-2" data-name="Email 2" placeholder="Enter your zip code" id="accountZip" required=""></div>
      </div>
    </form>
    <div class="w-form-done">
      <div>Thank you! Your submission has been received!</div>
    </div>
    <div class="w-form-fail">
      <div>Oops! Something went wrong while submitting the form.</div>
    </div><a href="#" class="button-6 w-button">Update Profile</a></div>
  <script src="https://code.jquery.com/jquery-3.3.1.min.js" type="text/javascript" intergrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
  <script src="js/webflow.js" type="text/javascript"></script>
  <!-- [if lte IE 9]><script src="https://cdnjs.cloudflare.com/ajax/libs/placeholders/3.0.2/placeholders.min.js"></script><![endif] -->
</body>
</html>