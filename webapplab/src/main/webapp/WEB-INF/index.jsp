<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>CountDownApp</title>
	<script type="text/javascript" src="js/angular.min.js"></script>
</head>
<body ng-app="app">
	Bonjour ${user} <br />
	 <br />
	<div ng-controller="inputCtrl">
		<label>Nom : </label><input type="text" ng-model="name" value=""><br />
		<label>Date compteur : </label><input type="datetime-local" ng-model="countdown" value="" ><br />
		<label>Fuseau horaire : </label><select ng-init="timezone='+01:00'" ng-model="timezone">
		      <option value="-12:00">(GMT -12:00) Eniwetok, Kwajalein</option>
		      <option value="-11:00">(GMT -11:00) Midway Island, Samoa</option>
		      <option value="-10:00">(GMT -10:00) Hawaii</option>
		      <option value="-09:00">(GMT -9:00) Alaska</option>
		      <option value="-08:00">(GMT -8:00) Pacific Time (US &amp; Canada)</option>
		      <option value="-07:00">(GMT -7:00) Mountain Time (US &amp; Canada)</option>
		      <option value="-06:00">(GMT -6:00) Central Time (US &amp; Canada), Mexico City</option>
		      <option value="-05:00">(GMT -5:00) Eastern Time (US &amp; Canada), Bogota, Lima</option>
		      <option value="-04:00">(GMT -4:00) Atlantic Time (Canada), Caracas, La Paz</option>
		      <option value="-03:30">(GMT -3:30) Newfoundland</option>
		      <option value="-03:00">(GMT -3:00) Brazil, Buenos Aires, Georgetown</option>
		      <option value="-02:00">(GMT -2:00) Mid-Atlantic</option>
		      <option value="-01:00">(GMT -1:00 hour) Azores, Cape Verde Islands</option>
		      <option value="Z">(GMT) Western Europe Time, London, Lisbon, Casablanca</option>
		      <option value="+01:00">(GMT +1:00 hour) Brussels, Copenhagen, Madrid, Paris</option>
		      <option value="+02:00">(GMT +2:00) Kaliningrad, South Africa</option>
		      <option value="+03:00">(GMT +3:00) Baghdad, Riyadh, Moscow, St. Petersburg</option>
		      <option value="+03:30">(GMT +3:30) Tehran</option>
		      <option value="+04:00">(GMT +4:00) Abu Dhabi, Muscat, Baku, Tbilisi</option>
		      <option value="+04:30">(GMT +4:30) Kabul</option>
		      <option value="+05:00">(GMT +5:00) Ekaterinburg, Islamabad, Karachi, Tashkent</option>
		      <option value="+05:30">(GMT +5:30) Bombay, Calcutta, Madras, New Delhi</option>
		      <option value="+05:45">(GMT +5:45) Kathmandu</option>
		      <option value="+06:00">(GMT +6:00) Almaty, Dhaka, Colombo</option>
		      <option value="+07:00">(GMT +7:00) Bangkok, Hanoi, Jakarta</option>
		      <option value="+08:00">(GMT +8:00) Beijing, Perth, Singapore, Hong Kong</option>
		      <option value="+09:00">(GMT +9:00) Tokyo, Seoul, Osaka, Sapporo, Yakutsk</option>
		      <option value="+09:30">(GMT +9:30) Adelaide, Darwin</option>
		      <option value="+10:00">(GMT +10:00) Eastern Australia, Guam, Vladivostok</option>
		      <option value="+11:00">(GMT +11:00) Magadan, Solomon Islands, New Caledonia</option>
		      <option value="+12:00">(GMT +12:00) Auckland, Wellington, Fiji, Kamchatka</option>
		</select>
		<button ng-click="event()">{{action}}</button><br />
	</div>
	 <br />
	<div ng-controller="countdownCtrl">
		<div ng-repeat="count in countdowns">
			<div>
				{{count.name}} : {{count.value}} 
				<button ng-click="editCountdown(count.id)">modifier</button>
				<button ng-click="removeCountdown(count.id)">x</button>
			</div>
		</div>
	</div>

	<input type="hidden" id="userid" value="${user }">
	
</body>

<script type="text/javascript">
	var userid = document.getElementById("userid").value;
	var socket = new WebSocket("ws://"+window.location.host+window.location.pathname+"websocket/"+userid);
	
	var app = angular.module("app", []);
	var inputScope;
	
	app.controller("inputCtrl", function($scope){
		inputScope = $scope;
		
		$scope.action = "Ajouter";
		
		$scope.addCountdown = function(){
			socket.send(JSON.stringify({
				action:"create",
				userid:userid,
				name:$scope.name,
				date:$scope.countdown.toISOString().split(".")[0],
				timezone:$scope.timezone
			}));
			$scope.name = "";
			$scope.countdown = "";
			$scope.timezone = "";
		};
		
		$scope.event = $scope.addCountdown;
		
		$scope.edit = function(countdown){
			$scope.action = "Modifier";
			$scope.id = countdown.id;
			$scope.event = $scope.updateCountdown;
			$scope.name = countdown.name;
			$scope.countdown = new Date(countdown.date);
			$scope.timezone = countdown.timezone;
		};
		
		$scope.updateCountdown = function(){
			console.log("updating "+$scope.id);
			socket.send(JSON.stringify({
				action:"update",
				id:$scope.id,
				userid:userid,
				name:$scope.name,
				date:$scope.countdown.toISOString().split(".")[0],
				timezone:$scope.timezone
			}));
			$scope.action = "Ajouter";
			$scope.event = $scope.addCountdown;
			$scope.name = "";
			$scope.countdown = "";
			$scope.timezone = "";
		};
	});
	app.controller("countdownCtrl", function($scope){
		$scope.countdowns =  [];
		socket.onmessage = function(msg){
			$scope.countdowns = JSON.parse(msg.data);
			$scope.$apply();
		};
		
		$scope.editCountdown = function(id){
			for(i in $scope.countdowns){
				if($scope.countdowns[i].id == id){
					inputScope.edit($scope.countdowns[i]);
				}
			}
		};
		
		$scope.removeCountdown = function(id){
			socket.send(JSON.stringify({
				action:"delete",
				userid:userid,
				id:id
			}));
		};
	});
</script>
</html>