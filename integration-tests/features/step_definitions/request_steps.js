// jshint esversion: 6
const { Given, When } = require("cucumber");
const axios = require('axios').default;

const { payloads } = require('../support/payloads.js');

Given(/I generate a json payload called \'(.*)\'/, function (payloadIdentifier) {
  if(payloads[payloadIdentifier]) {
    this.world.request.body = payloads[payloadIdentifier];
  } else {
    throw new Error(`the required payload ${payloadIdentifier} is not defined in features/support/payloads.js`);
  }
});
Given(/I generate a random id json payload called \'(.*)\'/, function (payloadIdentifier) {
	  if(payloads[payloadIdentifier]) {
	    this.world.request.body = payloads[payloadIdentifier];
	    this.world.request.id = uuidv4();
	  } else {
	    throw new Error(`the required payload ${payloadIdentifier} is not defined in features/support/payloads.js`);
	  }
	});

When(/I make a (\w+) to the function at \'(.*)\'/, function (httpMethod, path) {
  this.world.response = axios.request({
    url: path,
    baseURL: this.world.getFunctionBaseUri(),
    data: this.world.request.body,
    method: httpMethod,
    validateStatus: function (status) {
      return status >= 200 && status < 503; // default
    },
    headers: this.world.request.headers
  });
});

function uuidv4() {
	  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
	    var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
	    return v.toString(16);
	  });
	}