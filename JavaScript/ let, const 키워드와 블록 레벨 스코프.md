## var 키워드로 선언한 변수의 문제점<br>  
### 변수 중복 선언 허용<br>  
```javascript  
var x = 1;
var y = 1;

// var 키워드로 선언된 변수는 같은 스코프 내에서 중복 선언을 허용한다.
// 초기화 문이 있는 변수 선언문은 자바스크립트 엔진에 의해 var 키워드가 없는 것처럼 동작한다.
var x = 100;
// 초기화 문이 없는 변수 선언문은 무시된다.
var y;

console.log(x); // 100
console.log(y); // 1   
```  
  
### 함수 레벨 스코프<br>  
var 키워드로 선언한 변수는 오로지 함수의 코드 블록만을 지역 스코프로 인정한다.  
```javascript  
 var x = 1;
 
 if (true) {
	 // x는 전역 변수다. 이미 선언된 전역 변수 x가 있으므로 x 변수는 중복 선언된다.
	 // 이는 의도치 않게 변수값이 변경되는 부작용을 발생시킨다.
	 var x = 10;
 }
 
 console.log(x); // 10  
```  
  
```javascript  
var i = 10;

// for문에서 선언한 i는 전역 변수다. 이미 선언된 전역 변수 i가 있으므로 중복 선언된다.
for (var i = 0; i < 5; i++) {
	console.log(i); // 0 1 2 3 4
}

// 의도치 않게 i 변수의 값이 변경되었다.
console.log(i); // 5  
```  
함수 레벨 스코프는 전역 변수를 남발할 가능성을 높인다.  
  
### 변수 호이스팅<br>  
var 키워드로 변수를 선언하면 변수 호이스팅에 의해 변수 선언문이 스코프의 선두로 끌어 올려진 것처럼 동작한다.  
```javascript  
// 이 시점에는 변수 호이스팅에 의해 이미 foo 변수가 선언되었다(1. 선언 단계)
// 변수 foo는 undefined로 초기화된다(2. 초기화 단계)
console.log(foo); // undefined

// 변수에 값을 할당(3. 할당 단계)
foo = 123;

console.log(foo);

// 변수 선언은 런타임 이전에 자바스크립트 엔진에 의해 암묵적으로 실행된다.
var foo;  
```  
에러를 발생시키진 않지만 가독성을 떨어트리고 오류를 발생시킬 여지를 남긴다.  
  
## let 키워드<br>  
### 변수 중복 선언 금지<br>  
이름이 같은 변수를 중복 선언하면 문법 에러(SyntaxError)가 발생한다.  
```javascript  
var foo = 123;
// var 키워드로 선언된 변수는 같은 스코프 내에서 중복 선언을 허용한다.
// 아래 변수 선언문은 자바스크립트 엔진에 의해 var 키워드가 없는 것처럼 동작한다.
var foo = 456;

let bar = 123;
// let이나 const 키워드로 선언된 변수는 같은 스코프 내에서 중복 선언을 허용하지 않는다.
let bar = 456; // SyntaxError: Identifier 'bar' has already been declared   
```  
  
### 블록 레벨 스코프<br>  
모든 코드 블록(함수, if문, for문, while문 try/catch문 등)을 지역 스코프로 인정하는 블록 레벨 스코프(block-level scope)를 따른다.  
```javascript  
let foo = 1; // 전역 변수

{
	let foo = 2; // 지역 변수
	let bar = 3; // 지역 변수
}

console.log(foo); // 1
console.log(bar); // ReferenceError: bar is not defined  
```  
  
### 변수 호이스팅<br>  
let 키워드로 선언한 변수는 **“선언 단계”와 “초기화 단계”가 분리되어 진행된다.** 런타임 이전에 암묵적으로 선언 단계가 먼저 실행되지만 초기화 단계는 변수 선언문에 도달했을 때 실행된다.  
스코프의 시작 지점 부터 초기화 단계 시작 지점(변수 선언문)까지 변수를 참조할 수 없다. 이 구간을 **일시적 사각지대(Temporal Dead Zone; TDZ)**라고 부른다.  
```javascript  
// 런타임 이전에 선언 단계가 실행된다.
// 초기화 이전의 일시적 사각지대에서는 변수를 참조할 수 없다.
console.log(foo); // ReferenceError: foo is not defined

let foo; // 변수 선언문에서 초기화 단계가 실행된다.
console.log(foo); // undefined

foo = 1; // 할당문에서 할당 단계가 실행된다.
console.log(foo); // 1  
```  
  
```javascript  
let foo = 1; // 전역 변수

{
	console.log(foo); // ReferenceError: Cannot access 'foo' before initialization
	let foo = 2; // 지역 변수
}  
```  
let 키워드로 선언한 변수는 변수 호이스팅이 발생하지 않는 것처럼 보이지만 호이스팅이 발생하기 때문에 참조 에러(ReferenceError)가 발생한다.  
  
자바스크립트는 모든 선언을 호이스팅한다. 단, ES6에서 도입된 let, const, class를 사용한 선언문은 호이스팅이 발생하지 않는 것처럼 동작한다.  
  
### 전역 객체와 let<br>  
var 키워드로 선언한 전역 변수와 전역 함수, 그리고 선언하지 않은 변수에 값을 할당한 암묵적 전역은 전역 객체 window의 프로퍼티가 된다.  
```javascript  
// 이 예제는 브라우저 환경에서 실행해야 한다.

// 전역 변수
var x = 1;
// 암묵적 전역
y = 2;
// 전역 함수
function foo() {}

// var 키워드로 선언한 전역 변수는 전역 객체 window의 프로퍼티다.
console.log(window.x); // 1
// 전역 객체 window의 프로퍼티는 전역 변수처럼 사용할 수 있다.
console.log(x); // 1

// 암묵적 전역은 전역 객체 window의 프로퍼티다.
console.log(window.y); // 2
console.log(y); // 2

// 함수 선언문으로 정의한 전역 함수는 전역 객체 window의 프로퍼티다.
console.log(window.foo); // f foo() {}
// 전역 객체 window의 프로퍼티는 전역 변수처럼 사용할 수 있다.
console.log(foo); // f foo() {}  
```  
  
let 키워드로 선언한 전역 변수는 전역 객체의 프로퍼티가 아니다. let 전역 변수는 보이지 않는 개념적인 블록(전역 렉시컬 환경의 선언적 환경 레코드) 내에 존재하게 된다.  
```javascript  
// 이 예제는 브라우저 환경에서 실행해야 한다.
let x = 1;

// let, const 키워드로 선언한 전역 변수는 전역 객체 window의 프로퍼티가 아니다.
console.log(window.x); // undefined
console.log(x); // 1  
```  
  
  
## const 키워드<br>  
const 키워드는 상수(constant)를 선언하기 위해 사용한다. 하지만 반드시 상수만을 위해 사용하지는 않는다. 특징은 let 키워드와 대부분 동일한다.  
  
### 선언과 초기화<br>  
**const 키워드로 선언한 변수는 반드시 선언과 동시에 초기화 해야한다.**  
```javascript  
const foo; // SyntaxError: Missing initializer in const declration  
```  
  
let 키워드로 선언한 변수와 마찬가지로 블록 레벨 스코프를 가지며, 변수 호이스팅이 발생하지 않는 것처러 동장한다.  
```javascript  
{
	// 변수 호이스팅이 발생하지 않는 것처럼 동작한다.
	console.log(foo); // ReferenceError: Cannot access 'foo' before initialization
	const foo = 1;
	console.log(foo); // 1
}

// 블록 레벨 스코프를 갖는다.
console.log(foo); // ReferenceError: foo is not defined  
```  
  
### 재할당 금지<br>  
**const 키워드로 선언한 변수는 재할당이 금지된다.**  
```javascript  
const foo = 1;
foo = 2; // TypeError: Assignment to constant variable.  
```  
  
### 상수<br>  
**상수는 재할당이 금지된 변수를 말한다.**  
상수는 상태 유지와 가독성, 유지보수의 편의를 위해 적극적으로 사용해야 한다.  
```javascript  
// 세전 가격
let preTaxPrice = 100;

// 세후 가격
// 0.1의 의미를 명확히 알기 어렵기 때문에 가독성이 좋지 않다.
let afterTaxPrice = preTaxPrice + (preTaxPrice * 0.1);

console.log(afterTaxPrice); // 110  
```  
**const 키워드로 선언된 변수에 윈시 값을 할당한 경우 원시 값은 변경할 수 없는 값(immutable value)이고 const 키워드에 의해 재할당이 금지되므로 할당된 값을 변경할 수 있는 방법은 없다.** 이러한 특징을 이용해 상수를 표현하는 데 사용하기도 한다.  
  
일반적으로 상수의 이름은 대문자로 선언해 상수임을 명확히 나타낸다. 여러 단어로 이뤄진 경우에는 언더스코어(_)로 구분해서 스네이크 케이스로 표현하는 것이 일반적이다.  
```javascript  
// 세율을 의마하는 0.1은 변경할 수 없는 상수로서 사용될 값이다.
// 변수 이름을 대문자로 선언해 상수임을 명확히 나타낸다.
const TAX_RATE = 0.1;

// 세전 가격
let preTaxPrice = 100;

// 세후 가격
let afterTaxPrice = preTaxPrice + (preTaxPrice * TAX_RATE);

console.log(afterTaxPrice); // 110  
```  
  
### const 키워드와 객체<br>  
const 키워드로 선언된 변수에 객체를 할당항 경우 값을 변경할 수 있다.  
```javascript  
const person = {
	name: 'Lee'
};

// 객체는 변경 가능한 값이다. 따라서 재할당 없이 변경이 가능하다.
person.name = 'Kim';

console.log(person); // {name: "kim"}  
```  
  
**const 키워드는 재할당을 금지할 뿐 “불변”을 의미하지는 않는다.** 프로퍼티 동적 생성, 삭제, 프로퍼티 값의 변경을 통해 객체를 변경하는 것은 가능하다. 이때 객체가 변경되더라도 변수에 할당된 참조 값은 변경되지 않는다.  
  
## var vs. let vs. const<br>  
변수 선언에는 기본적으로 const를 사용하고 let은 재할당이 필요한 경우에 한정해 사용하는 것이 좋다.  
var와 let, const 키워드는 다음과 같이 사용하는 것을 권장한다.  
* ES6을 사용한다면 var 키워드는 사용하지 않는다.  
* 재할당이 필요한 경우에 한정해 let 키워드를 사용한다. 이떄 변수의 스코프는 최대한 좁게 만든다.  
* 변경이 발생하지 않고 읽기 전용으로 사용하는(재할당이 필요 없는 상수) 원시 값과 객체에는 const 키워드를 사용한다. const 키워드는 재할당을 금지하므로 var, let 키워드보다 안전하다.  
  
