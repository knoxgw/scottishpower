package com.example.scottishpower.util

class PlaceholderNetworkingException(httpStatus: String): Exception("Placeholder API failed with HTTP status: $httpStatus")

class PlaceholderEmptyResponseException: Exception("Placeholder API responded with empty body")