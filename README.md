# KNU_DatabaseTeam5
KNU database project repository

<경북대학교 데이터베이스 KNU Movie site>
DB 구축
	‘DB 생성’ 디렉토리의 sql 파일 활용, sql 파일들은 oracle 동작 기준으로 작성됨
	‘1. table create’ 의 파일을 통해 table 생성
	‘2. data population’의 파일을 통해 tuple population
	이후 commit 해주어야 함!
	admin 계정은 : (admin1 / admin) ,(admin2 / admin), (admin3 / admin)
환경설정 properties
	app.properties 내부에는 
		region=KR (서버 지역)
	db.properties 내부에는
		driver=oracle.jdbc.driver.OracleDriver (jdbc DB driver)
		url=jdbc:oracle:thin:@localhost:32769:orcl (DB url) 
		username=university (DB user name)
		password=comp322 (DB user password)
실행방법
	*모든 실행 파일들은 제출물의 ‘Phase4 실행파일’ 폴더 안에 있습니다.
	back-end server jar file 경로로 terminal 을 연다.
	app.properties, db.properties 파일을 db 설정에 맞게 변경한다.
	아래의 명령어로 back-end server를 실행한다. java 11 버전 이상을 지원한다.
		java -Dapp.properties="./app.properties" -Ddb.properties="./db.properties" -jar app-1.0.1.jar
	front-end UI 폴더 경로로 terminal 을 연다.
	아래의 명령어를 입력한다. Node.js 가 설치되어 있어야한다.
		npm install (dependencies install)
		npm start
	해당 명령어를 입력하게 되면 의존성 설치가 되고, 웹 UI가 브라우저에서 실행된다. 3000번 기본 포트로 실행해야한다.
