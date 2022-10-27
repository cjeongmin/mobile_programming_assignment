개발환경: Mac OS Ventura 13.0
        Android Studio Dolphin | 2021.3.1 Patch 1

컴파일 환경: Compile SDK Version
           - 32 (API 32: Android 12L (Sv2))
          Virtual Device API level
           - 31
          Device Name
           - Pixel 4 API 31 

---

구현 내용
1. 첫번째 화면 (Sign In)
 - 앱 접속 페이지로써 처음 켜지는 화면에서 SharedPreference 객체에 접근하여 유저 목록을 가져옵니다.
 - 앱 접속 페이지로 들어온 화면에서 회원 ID/Password를 입력하는 View가 2개, 로그인/회원가입/Home 버튼이 3개로 총 5개의 View로 화면을 구성하고 있습니다.
 - ID와 비밀번호를 입력하고 로그인 시도시 없는 ID면 존재하지 않는 ID라고 Toast 메세지를 보냅니다. ID가 존재하나 비밀번호가 다르다면 또한 비밀번호가 일치하지 않다는 메세지를 보냅니다. 로그인에 성공하면 로그인에 성공하였다는 Toast 메세지를 보냅니다. 만약, ID/비밀번호를 모두 입력하지 않을시에도 경고 Toast 메세지를 보냅니다.
 - Sign Up 버튼을 클릭시 회원가입 화면으로 넘어갑니다.
 - Home 버튼을 클릭시 비로그인 상태로 상품 화면으로 넘어갑니다.
 - Sign In 버튼을 클릭하여 로그인 상태로 상품 화면으로 넘어가게 되면 로그인 화면 액티비티는 종료하게 됩니다.

2. 두번째 화면 (Sign Up)
 - ID, 비밀번호, 이름, 전화번호, 주소를 입력받는 EditText View가 5개, 개인정보 동의를 받는 Radio Button이 2개, 제출 버튼이 하나로 두번째 화면을 구성하고 있습니다.
 - EditText View가 만약 비어있는 값이 있다면 입력을 하도록 경고 Toast 메세지를 보냅니다.
 - 중복된 ID가 있다면 경고 Toast 메세지를 보냅니다.
 - 비밀번호, 이름, 주소의 최대 길이를 넘을 시 경고 Toast 메세지를 보냅니다.
 - 전화번호의 형식은 000-0000-0000 으로 강제하여 다른 형식의 전화번호가 들어오면 경고 Toast 메세지를 보냅니다.
 - ID는 영어대소문자/숫자, 비밀번호는 영어 대소문자/숫자/특수기호(~, !, @), 이름은 영어 대소문자, 숫자, 한글, 특수기호(~, !)으로만 입력 받을 수 있게 강제하였고 주소는 이름과 같은 조건에다가 공백을 추가로 받을 수 있습니다. 그 외의 입력이 들어오면 경고 Toast 메세지를 보냅니다.
 - 개인정보 수집 동의/비동의 여부에 따라 해당 Toast 메세지를 보냅니다.
 - 위의 모든 조건을 통과하면 회원가입을 성공하며 회원 목록에 추가하고 SharedPreference를 통해 데이터를 저장합니다.

3. 세번째 화면 (Main Activity)
 - Frame Layout 위에 Recycler View를 이용해서 상품명, 상품이미지 리스트를 보여주고 있는 화면을 구현했습니다.
 - 바텀 액션 바에서 사람 모양을 누르면 로그인시에는 유저 정보를, 비로그인시에는 간단한 모달 창을 보여준 후에 사용자의 의도에 따라 회원가입 화면으로 이동 여부를 결정합니다.