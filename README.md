# Web Management System
****
簡易後台員工管理系統
****
基於SpringBoot及MyBatisPlus框架完成的簡易後台員工管理系統
****
技術使用:

1.配置專案開發所需Maven文件

2.基於MVC架構完成程式撰寫

3.使用Thymeleaf模擬前端回傳資料

4.調用API功能如:VerifyCodeUtils 完成圖形碼驗證碼功能,Jayspt對重要資訊進行加密,DigestUtils完成MD5加密

5.配置Cors同源跨域資源共享

6.使用MyBatisPlus對資料庫進行增刪改查操作

7.撰寫Test類進行單元測試

8.利用Advice攔截未知異常，進行頁面跳轉

功能介紹:

1.註冊帳號密碼，會檢查是否與資料庫中已存在的帳號衝突，如衝突則跳轉到指定頁面
![帳號重複註冊](https://user-images.githubusercontent.com/111175201/188304743-4ad13cc4-380d-4ef3-9feb-bb7b6707da5a.gif)

2.判斷驗證碼是否與圖形相等
![驗證碼輸入錯誤](https://user-images.githubusercontent.com/111175201/188304785-d82e45a5-1508-4dd8-82ff-0940db08275c.gif)

3.沒有註冊的帳號，引導至註冊頁面
![帳號不存在引導註冊](https://user-images.githubusercontent.com/111175201/188304836-ca70f0bf-ebd4-4e3e-a009-4f78c6f1a590.gif)

4.登入輸入密碼後進行MD5加密，與資料庫中的MD5密碼進行比對，比對正確及登入，錯誤則提示後跳轉頁面

![登入成功跳轉至emplist](https://user-images.githubusercontent.com/111175201/188304941-450c99e7-d1de-4987-b71c-e6d7d408e33a.gif)

5.新增員工並上傳照片
![新增員工](https://user-images.githubusercontent.com/111175201/188305300-8d557d53-31d3-4e0d-a98c-5a2c89c971ae.gif)

6.更新及刪除員工資訊
![更新及刪除員工](https://user-images.githubusercontent.com/111175201/188305359-ef14485d-ff20-470d-bb47-8ea4e6e307e9.gif)

7.下載成Excel檔案到電腦裡
![導出員工資料表](https://user-images.githubusercontent.com/111175201/188305468-89352604-0757-41f2-97f6-5d99341553f1.gif)

8.安全登出後會自動清除Session內的資訊，攔截未登入帳號的Session請求
![安全登出](https://user-images.githubusercontent.com/111175201/188305733-99e3044f-4df1-4c7c-8ff1-c3d6e3b13350.gif)


