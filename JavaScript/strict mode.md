## strict mode란?<br>  
```javascript  
function foo() {
	x = 10;
}
foo();

console.log(x); // ?  
```  
위 예제의 지역, 전역 스코프에 x 변수의 선언이 존재하지 않기 때문에 ReferenceError를 발생시킬 것 같지만 자바스크립트 엔진은 암묵적으로 전역 객체에 x 프로퍼티를 동적 생성한다. 이때 전역 객체의 x 프로퍼티는 마치 전역 변수처럼 사용할 수 있다. 이러한 현상을 **암묵적 전역(implicit global)**이라 한다.  
암묵적 전역은 오류를 발생시키는 원인이 될 가능성이 크다. 따라서 <ins>반드시 var, let, const 키워드를 사용하여 변수를 선언</ins>한 다음 사용해야 한다.  
  
ES5부터 추가된 strict mode(엄격 모드)는 자바스크립트 언어의 문법을 좀 더 엄격히 적용하여 오류를 발생시킬 가능성이 높거나, 자바스크립트 엔진의 최적화 작업에 문제를 일으킬 수 있는 코드에 대해 명시적인 에러를 발생시킨다.  
ESLint 같은 린트 도구를 사용해도 strict mode와 유사한 효과를 얻을 수 있다.  
  
ES6에서 도입된 클래스와 모듈은 기본적으로 strict mode가 적용된다.  
  
---  
  
## strict mode의 적용<br>  
strict mode를 적용하려면 전역의 선두 또는 함수 몸체의 선두에 ‘use strict’;를 추가한다.  
  
전역 선두에 추가하면 스크립트 전체에 strict mode가 적용된다.  
```javascript  
'use strict';

function foo() {
	x = 10; // ReferenceError: x is not defined
}
foo();  
```  
함수 몸체의 선두에 추가하면 해당 함수와 중첩 함수에 strict mode가 적용된다.  
```javascript  
function foo() {
	'use strict';
	
	x = 10; // ReferenceError: x is not defined
}
foo();  
```  
코드의 선두에 ‘use strict’;를 위치시키지 않으면 strict mode가 제대로 동작하지 않는다.  
```javascript  
function foo() {
	x = 10; // 에러를 발생시키지 않는다.
	'use strict';
}
foo();  
```  
  
---  
  
## 전역에 strict mode를 적용하는 것은 피하자<br>  
전역에 적용한 strict mode는 스크립트 단위로 적용된다.  
```html  
<!DOCTYPE html>
<html>
 <body>
	 <script>
		 'use strict';
	 </script>
	 <script>
		 x = 1; // 에러가 발생하지 않는다.
		 console.log(x); // 1
	 </script>
	 <script>
		 'use strict';
		 
		 y = 1; // ReferenceError: y is not defined
	 </script>
 </body>
</html>  
```  
strict mode 스크립트와 non-strict mode 스크립트를 혼용하는 것은 오류를 발생시킬 수 있다. 외부 서드파티 라이브러리를 사용하는 경우 no-strict mode인 경우도 있기 때문에 전역에 strict mode를 적용하는 것은 바람직하지 않다.  
이러한 경우 즉시 실행 함수로 스크립트 전체를 감싸서 스코프를 구분하고 즉시 실행 함수의 선두에 strict mode를 적용한다.  
```javascript  
// 즉시 실행 함수의 선두에 strict mode 적용
(function () {
	'use strict';
	
	// Do something...
}());  
```  
  
---  
  
## 함수 단위로 strict mode를 적용하는 것도 피하자<br>  
```javascript  
(function () {
	// non-strict mode
	var let = 10; // 에러가 발생하지 않는다.
	
	function foo() {
		'use strict';
		
		let = 20; // SyntaxError: Unexpected strict mode reserved word
	}
	foo();
}());  
```  
strict mode는 즉시 실행 함수로 감싼 스크립트 단위로 적용하는 것이 바람직하다.  
  
---  
  
## strict mode가 발생시키는 에러<br>  
### 암묵적 전역<br>  
선언하지 않은 변수를 참조하면 ReferenceError가 발생한다.  
```javascript  
(function () {
	'use strict';
	
	x = 1;
	console.log(x); // ReferenceError: x is not defined
}());  
```  
  
### 변수, 함수, 매개변수의 삭제<br>  
delete 연산자로 변수, 함수, 매개변수를 삭제하면 SyntaxError가 발생한다.  
```javascript  
(function () {
	'use strict';
	
	var x = 1;
	delete x; // SyntaxError: Delete of an unqualified identifier in strict mode.
	
	function foo(a) {
		delete a; // SyntaxError: Delete of an unqualified identifier in strict mode.
	} 
	delete foo; // SyntaxError: Delete of an unqualified identifier in strict mode.
}());  
```  
  
### 매개변수 이름의 중복<br>  
중복된 매개변수 이름을 사용하면 SyntaxError가 발생한다.  
```javascript  
(function () {
	'use strict';
	
	// SyntaxError: Duplicate parameter name not allowed in this context
	function foo(x, x) {
		return x + x;
	}
	
	console.log(foo(1, 2));
}());  
```  
  
### with 문의 사용<br>  
with 문을 사용하면 SyntaxError가 발생한다. with 문은 전달된 객체를 스코프 체인에 추가한다. 동일한 객체의 프로퍼티를 반복해서 사용할 때 객체의 이름을 생략할 수 있어 코드가 간단해지는 효과가 있지만 성능과 가독성이 나빠지는 문제가 있다.  
```javascript  
(function () {
	'use strict';
	
	// SyntaxError: Strict mode code may not include a with statement
	with({ x: 1 }) {
		console.log(x);
	}
}());  
```  
  
---  
  
## strict mode 적용에 의한 변화<br>  
### 일반 함수의 this<br>  
strict mode에서 함수를 일반 함수로서 호출하면 this에 undefined가 바인딩된다. 생성자 함수가 아닌 일반 함수 내부에서는 this를 사용할 필요가 없기 때문이다. 에러는 발생하지 않는다.  
```javascript  
(function () {
	'use strict';
	
	function foo() {
		console.log(this); // undefined
	}
	foo();
	
	function Foo() {
		console.log(this); // Foo
	}
	new Foo();
}());  
```  
  
### arguments 객체<br>  
strict mode에서는 매개변수에 전달된 인수를 재할당하여 변경해도 arguments 객체에 반영되지 않는다.  
```javascript  
(function (a) {
	'use strict';
	// 매개변수에 전달된 인수를 재할당하여 변경
	a = 2;
	
	// 변경된 인수가 arguments 객체에 반영되지 않는다.
	console.log(arguments); // { 0: 1, length: 1 }
}(1));  
```  
  
