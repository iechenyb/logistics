api
	http://localhost:8080/swagger-ui.html 

未限制的方法
	curl "http://localhost:8080/demo/findAll" -X GET
	返回
	[
	  {
	    "id": 1,
	    "name": "xsc1",
	    "date": "1985-09-20"
	  },
	  {
	    "id": 16,
	    "name": "xsc2",
	    "date": "1985-09-20"
	  }
	]
	
访问限制的方法
	curl "http://localhost:8080/demo/1" -X GET
	返回
	{
	  "error": "unauthorized",
	  "error_description": "Full authentication is required to access this resource"
	}

获取token basic auth
	curl "http://localhost:8080/oauth/token" -X POST -u client:m_volunteer -d "grant_type=password&username=voltest&password=voltest"
	授权服务器验证了我们的客户端和用户信息，验证成功后将我们需要的令牌（token）信息作为响应传回：
	{
	  "access_token": "363860cb-253e-40e2-a719-7e447f147f97",
	  "token_type": "bearer",
	  "refresh_token": "e6cd3e81-6f18-4362-bded-24baa79dbf47",
	  "expires_in": 2591999,
	  "scope": "read write"
	}
	
用token请求限制的url
	curl "http://localhost:8080/demo/1" -X GET -H "Authorization: Bearer 363860cb-253e-40e2-a719-7e447f147f97"
	返回
	{
	  "id": 1,
	  "name": "xsc1",
	  "date": "1985-09-20"
	}
