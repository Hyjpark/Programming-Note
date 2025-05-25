> DOM(Document Object Model)은 HTML 문서의 계층적 구조와 정보를 표현하며 이를 제어할 수 있는 API, 즉 프로퍼티와 메서드를 제공하는 트리 자료구조다.  
  
  
## 노드<br>  
### HTML 요소와 노드 객체<br>  
HTML 요소(HTML element)는 HTML 문서를 구성하는 개별적인 요소를 의미한다.  
* 시작 태그  
* 어트리뷰트 이름  
* 어트리뷰트 값  
* 콘텐츠  
* 종료 태그  
HTML 요소는 렌더링 엔진에 의해 파싱되어 DOM을 구성하는 요소 노드 객체료 변환된다. 이때 HTML 요소의 어트리뷰트는 어트리뷰트 노드로, 텍스트 콘텐츠는 텍스트 노드로 변환된다.   
HTML 요소 간에는 중첩 관게에 의해 계층적인 부자 관계가 형성된다. 이런 HTML 요소 간의 부자 관계를 반영하여 모든 노드 객체들을 트리 자료 구조로 구성한다.   
**노드 객체들로 구성된 트리 자료구조를 DOM**이라고 하며 **DOM 트리**라고 부르기도 한다.  
  
### 노드 객체의 타입<br>  
DOM은 노드 객체의 계층적인 구조로 구성된다. 노드 객체는 총 12개의 종류(노드 타입)가 있다. 이 중에서 중요한 노드 타입은 다음과 같이 4가지다.  
  
**문서 노드(document node)**  
최상위에 존재하는 루트 노드로서 document 객체를 가리킨다. document 객체는 브라우저가 렌더링한 HTML 문서 전체를 가리키는 객체로서 전역 객체 window의 document 프로퍼티에 바인딩되어 있다. 따라서 문서 노드는 window.document 또는 document로 참조할 수 있다.  
브라우저 환경의 모든 자바스크립트 코드는 script 태그에 의해 분리되어 있어도 하나의 전역 객체 window를 공유한다. 즉 HTML 문서당 document 객체는 유일하다.  
document 객체는 DOM 트리의 루트 노드이므로 DOM 트리의 노드들에 접근하기 위한 진입점 역할을 담당한다. 요소, 어트리뷰트, 텍스트 노드에 접근하려면 문서 노드를 통해야 한다.  
  
**요소 노드(element node)**  
HTML 요소를 가리키는 객체다. 요소 노드는 HTML 요소 간의 중첩에 의해 부자 관계를 가지며, 이 부자 관계를 통해 정보를 구조화한다. 따라서 요소 노드는 문서의 구조를 표현한다고 할 수 있다.  
  
**어트리뷰트 노드(attribute node)**  
HTML 요소의 어트리뷰트를 가리키는 객체다. 어트리뷰트 노드는 어트리뷰트가 지정된 HTML 요소의 요소 노드와 연결되어 있다. 단, 요소 노드는 부모 노드와 연결되어 있지만 어트리뷰트 노드는 부모 노드와 연결되어 있지 않고 요소 노드에만 연결되어 있다. 즉, 어트리뷰트 노드는 부모 노드가 없으므로 요소 노드의 형제 노드는 아니다. 따라서 어트리뷰트 노드에 접근하려면 먼저 요소 노드에 접근해야 한다.  
  
**텍스트 노드(text node)**  
HTML 요소의 텍스트를 가리키는 객체다. 문서의 정보를 표현한다고 할 수 있다. 텍스트 노드는 요소 노드의 자식 노드이며, 자식 노드를 가질 수 없는 리프 노드다. 즉, 텍스트 노드는 DOM 트리의 최동단이다. 따라서 텍스트 노드에 접근하려면 먼저 부모 노드인 요소 노드에 접근해야 한다.  
  
### 노드 객체의 상속 구조<br>  
**DOM은 HTML 문서의 계층적 구조와 정보를 표현하는 것은 몰론 노드 타입에 따라 필요한 기능을 프로퍼티와 메서드의 집합인 DOM API로 제공한다. 이 DOM API를 통해 HTML의 구조나 내용 또는 스타일 등을 동적으로 조작할 수 있다.**  
모든 노드 객체는 Object, EventTarget, Node 인터페이스를 상속 받는다. 문서 노드는 Document, HTMLDocument 인터페이스를 상속받고 어트리뷰트 노드는 Attr, 텍스트 노드는 CharacterData, 요소 노드는 Element 인터페이스를 각각 상속 받는다.  
  
---  
## 요소 노드 취득<br>  
HTML의 구조나 내용 또는 스타일 등을 동적으로 조작하려면 먼저 요소 노드를 취득해야 한다. 텍스트 노드는 요소 노드의 자식 노드이고, 어트리뷰트 노드는 요소 노드와 연결되어 있기 때문에 텍스트 노드나 어트리뷰트 노드를 조작하고자 할 때도 마찬가지다.  
### id를 이용한 요소 노드 취득<br>  
Document.prototype.getElementById 메서드는 인수로 전달한 id 어트리뷰트 값을 갖는 하나의 요소 노드를 탐색하여 반환한다. 반드시 문서 노드인 document를 통해 호출해야 한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul>
			<li id="apple">Apple</li>
			<li id="banana">Banana</li>
			<li id="orange">Orange</li>
		</ul>
		<script>
			// id 값이 'banana'인 요소 노드를 탐색하여 반환한다.
			// 두 번째 li 요소가 파싱되어 생성된 요소 노드가 반환된다.
			const $elem = document.getElementById('banana');
			
			// 취득한 요소 노드의 style.color 프로퍼티 값을 변경한다.
			$elem.style.color = 'red';
		</script>
	</body>
</html>  
```  
id 값은 HTML 문서 내에서 유일한 값이어야 하며, 공백 문자로 구분하여 여러 개의 값을 가질 수 없다. 단, HTML 문서 내에 중복된 id 값을 갖는 HTML 요소가 여러 개 존재하더라도 어떠한 에러도 발생하지 않는다. 이러한 경우 getElementById 메서드는 첫 번째 요소 노드만 반환한다.  
만약 인수로 전달된 id 값을 갖는 HTML 요소가 존재하지 않는 경우 getElementById 메서드는 null을 반환한다.  
HTML 요소에 id 어트리뷰트를 부여하면 id 값과 동일한 이름의 전역 변수가 암묵적으로 선언되고 해당 노드 객체가 할당되는 부수 효과가 있다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<div id="foo"></div>
		<script>
			// id 값과 동일한 이름의 전역 변수가 암묵적으로 선언되고 해당 노드 객체가 할당된다.
			console.log(foo === document.getElementById('foo')); // true
			
			// 암묵적 전역으로 생성된 전역 프로퍼티는 삭제되지만 전역 변수는 삭제되지 않는다.
			delete foo;
			console.log(foo); // <div id="foo"></div>
		</script>
	</body>
</html>  
```  
단, id 값과 동일한 이름의 전역 변수가 이미 선언되어 있으면 이 전역 변수에 노드 객체가 재할당되지 않는다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<div id="foo"></div>
		<script>
			let foo = 1;
			
			// id 값과 동일한 이름의 전역 변수가 이미 선언되어 있으면 노드 객체가 재할당되지 않는다.
			console.log(foo); // 1
		</script>
	</body>
</html>  
```  
  
### 태그 이름을 이용한 요소 노드 취득<br>  
Document.prototype/Element.prototype.getElementsByTagName 메서드는 인수로 전달한 태그 이름을 갖는 모든 요소 노드들을 탐색하여 반환한다. getElementsByTagName 메서드는 여러 개의 요소 노드 객체를 갖는 DOM 컬렉션 객체인 HTMLCollection 객체를 반환한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul>
			<li id="apple">Apple</li>
			<li id="banana">Banana</li>
			<li id="orange">Orange</li>
		</ul>
		<script>
			// 태그 이름이 li인 요소 노드를 모두 탐색하여 반환한다.
			// 탐색된 요소 노드들은 HTMLCollection 객체에 담겨 반환된다.
			// HTMLCollection 객체는 유사 배열 객체이면서 이터러블이다.
			const $elems = document.getElementsByTagName('li');
			
			// 취득한 모든 요소 노드의 style.color 프로퍼티 값을 변경한다.
			// HTMLCollection 객체를 배열로 변환하여 순회하며 color 프로퍼티 값을 변경한다.
			[...$elems].forEach(elem => { elem.style.color = 'red'; });
		</script>
	</body>
</html>  
```  
getElementsByTagName  메서드가 반환하는 HTMLCollection 객체는 유사 배열 객체이면서 이터러블이다.  
HTML 문서의 모든 요소 노드를 취득하려면 인수로 ‘*’를 전달한다.  
```javascript  
// 모든 요소 노드를 탐색하여 반환한다.
const $all = document.getElementsByTagName('*');
// -> HTMLCollection(8) [html, head, body, ul, li#apple, li#banana, li#orange, script, apple: li#apple, banana: li#banana, orange: li#orange]  
```  
  
* Document.prototype.getElementsByTagName  → DOM의 루트 노드인 문서 노드, 즉 document를 호출하며 DOM 전체에서 요소 노드를 탐색하여 반환한다.  
* Element.prototype.getElementsByTagName  → 특정 요소 노드를 통해 호출하며, 특정 요소 노드의 자손 노드 중에서 요소 노드를 탐색하여 반환한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="fruits">
			<li>Apple</li>
			<li>Banana</li>
			<li>Orange</li>
		</ul>
		<ul>
			<li>HTML</li>
		</ul>
		<script>
			// DOM 전체에서 태그 이름이 li인 요소 노드를 모두 탐색하여 반환한다.
			const $lisFromDocument = document.getElementsByTagName('li');
			console.log($lisFromDocument); // HTMLCollection(4) [li, li, li, li]
			
			// ul#fruits 요소의 자손 노드 중에서 태그 이름이 li인 요소 노드를 모두 탐색하여 반환한다.
			const $fruits = document.getElementsByTagName('fruits');
			const $lisFromFruits = $fruits.getElementsByTagName('li');
			console.log($lisFromFruits); // HTMLCollection(3) [li, li, li]
		</script>
	</body>
</html>  
```  
만약 인수로 전달된 태그 이름을 갖는 요소가 존재하지 않는 경우 getElementsByTagName 메서드는 빈 HTMLCollection 객체를 반환한다.  
  
### class를 이용한 요소 노드 취득<br>  
Document.prototype/Element.prototype.getElementsByClassName 메서드는 인수로 전달한 class 어트리뷰트 값을 갖는 모든 요소 노드들을 탐색하여 반환한다. 인수로 전달할 class 값은 공백으로 구분하여 여러 개의 class를 지정할 수 있다. HTMLCollection 객체를 반환한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul>
			<li class="fruit apple">Apple</li>
			<li class="fruit banana">Banana</li>
			<li class="fruit orange">Orange</li>
		</ul>
		<script>
			 // class 값이 'fruit'인 요소 노드를 모두 탐색하여 HTMLCollection 객체에 담아 반환한다.
			 const $elems = document.getElementsByClassName('fruit');
			 
			 // 취득한 모든 요소의 CSS color 프로퍼티 값을 변경한다.
			 [...$elems].forEach(elem => { elem.style.color = 'red'; });
			 
			 // class 값이 'fruit apple'인 요소 노드를 모두 탐색하여 반환한다.
			 const $apples = document.getElementsByClassNmae('fruit apple');
			 
			 // 취득한 모든 요소의 CSS color 프로퍼티 값을 변경한다.
			 [...$apples].forEach(elem => { elem.style.color = 'blue'; });
		</script>
	</body>
</html>  
```  
* Document.prototype.getElementsByClassName  → document를 호출하며 DOM 전체에서 요소 노드를 탐색하여 반환한다.  
* Element.prototype.getElementsByClassName  → 특정 요소 노드를 통해 호출하며 특정 요소 노드의 자손 노드 중에서 요소 노드를 탐색하여 반환한다.  
만약 인수로 전달된 태그 이름을 갖는 요소가 존재하지 않는 경우 getElementsByClassName 메서드는 빈 HTMLCollection 객체를 반환한다.  
  
### CSS 선택자를 이용한 요소 노드 취득<br>  
```css  
/* 전체 선택자: 모든 요소를 선택 */
* { ... }
/* 태그 선택자: 모든 p 태그 요소를 모두 선택 */
p { ... }
/* id 선택자: id 값이 'foo'인 요소를 모두 선택 */
#foo { ... }
/* class 선택자: class 값이 'foo'인 요소를 모두 선택 */
.foo { ... }
/* 어트리뷰트 선택자: input 요소 중에 type 어트리뷰트 값이 'text'인 요소를 모두 선택 */
input[type=text] { ... }
/* 후손 선택자: div 요소의 후손 요소 중 p 요소를 모두 선택 */
div p { ... }
/* 자식 선택자: div 요소의 자식 요소 중 p 요소를 모두 선택 */
div > p { ... }
/* 인접 형제 선택자: p 요소의 형제 요소 중에 p 요소 바로 뒤에 위치하는 ul 요소를 선택 */
p + ul { ... }
/* 일반 형제 선택자: p 요소의 형제 요소 중에 p 요소 뒤에 위치하는 ul 요소를 모두 선택 */
p ~ ul { ... }
/* 가상 클래스 선택자: hover 상태인 a 요소를 모두 선택 */
a:hover { ... }
/* 가상 요소 선택자 : p 요소의 콘텐츠의 앞 뒤에 위치하는 공간을 선택
	 일반적으로 content 프로퍼티와 함께 사용된다. */
p::before { ... }  
```  
Document.prototype/Element.prototype.querySelector 메서드는 인수로 전달한 CSS 선택자를 만족시키는 하나의 요소 노드를 탐색하여 반환한다.  
* 인수로 전달한 CSS 선택자를 만족시키는 요소 노드가 여러 개인 경우 첫 번째 요소 노드만 반환한다.  
* 인수로 전달된 CSS 선택자를 만족시키는 요소 노드가 존재하지 않는 경우 null을 반환한다.  
* 인수로 전달한 CSS 선택자가 문법에 맞지 않는 경우 DOMException 에러가 발생한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul>
			<li class="apple">Apple</li>
			<li class="banana">Banana</li>
			<li class="orange">Orange</li>
		</ul>
		<script>
			// class 어트리뷰트 값이 'banana'인 첫 번째 요소 노드를 탐색하여 반환한다.
			const $elem = document.querySelector('banana');
			
			// 취득한 요소 노드의 style.color 프로퍼티 값을 변경한다.
			$elem.style.color = 'red';
		</script>
	</body>
</html>  
```  
Document.prototype/Element.prototype.querySelectorAll 메서드는 인수로 전달한 CSS 선택자를 만족시키는 모든 요소 노드를 탐색하여 반환한다. querySelectorAll 메서드는 여러 개의 요소 노드 객체를 갖는 DOM 컬렉션 객체인 NodeList 객체를 반환한다. NodeList 객체는 유사 배열 객체이면서 이터러블이다.  
* 인수로 전달된 CSS 선택자를 만족시키는 요소가 존재하지 않는 경우 빈 NodeList 객체를 반환한다.  
* 인수로 전달한 CSS 선택자가 문법에 맞지 않는 경우 DOMException 에러가 발생한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul>
			<li class="apple">Apple</li>
			<li class="banana">Banana</li>
			<li class="orange">Orange</li>
		</ul>
		<script>
			// ul 요소의 자식 요소인 li 요소를 모두 탐색하여 반환한다.
			const $elems = document.querySelectorAll('ul > li');
			// 취득한 요소 노드들은 NodeList 객체에 담겨 반환된다.
			console.log($elems); // NodeList(3) [li.apple, li.banana, li.orange]
			
			// 취득한 모든 요소 노드의 style.color 프로퍼티 값을 변경한다.
			// NodeList는 forEach 메서드를 제공한다.
			$elems.forEach(elem => { elem.style.color = 'red'; });
		</script>
	</body>
</html>  
```  
모든 요소를 취득하려면 인수로 전체 전택자 ‘*’를 전달한다.  
```javascript  
// 모든 요소 노드를 탐색하여 반환한다.
const $all = document.querySelectorAll('*');
// -> NodeList(8) [html, head, body, ul, li#apple, li#banana, li#orange, script]  
```  
  
* Document.prototype.querySelector/querySelectorAll   → document를 호출하며 DOM 전체에서 요소 노드를 탐색하여 반환한다.  
* Element.prototype.querySelector/querySelectorAll   → 특정 요소 노드를 통해 호출하며 특정 요소 노드의 자손 노드 중에서 요소 노드를 탐색하여 반환한다.  
  
CSS 선택자 문법을 사용하는 querySelector, querySelectorAll 메서드는 getElementById, getElementsBy\*\** 메서드보다 다소 느린 것으로 알려져 있다. 하지만 CSS 선택자 문법을 사용하여 좀 더 구체적인 조건과 일관된 방식으로 요소 노드를 취득할 수 있다는 장점이 있다.  
따라서 id 어트리뷰트가 있는 요소 노드를 취득하는 경우에는 getElementById 메서드를 사용하고 그 외의 경우에는 querySelector, querySelectorAll 메서드를 사용하는 것을 권장한다.  
  
### 특정 요소 노드를 취득할 수 있는지 확인<br>  
Element.prototype.matches 메서드는 인수로 전달한 CSS 선택자를 통해 특정 요소 노드를 취득할 수 있는지 확인한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="fruits">
			<li class="apple">Apple</li>
			<li class="banana">Banana</li>
			<li class="orange">Orange</li>
		</ul>
	</body>
	<script>
		const $apple = document.querySelector('.apple');
		
		// $apple 노드는 '#fruits > li.apple'로 취득할 수 있다.
		console.log($apple.matches('#fruits > li.apple')); // true
		
		// $apple 노드는 '#fruits > li.banana'로 취득할 수 없다.
		console.log($apple.matches('#fruits > li.banana')); // false
	</script>
</html>  
```  
  
### HTMLCollection과 NodeList<br>  
DOM API가 여러 개의 결과값을 반환하기 위한 DOM 컬렉션 객체다. HTMLCollection과 NodeList는 모두 유사 배열 객체이면서 이터러블이다. 따라서 for…of문으로 순회할 수 있으면 스프레드 문법을 사용하여 간단히 배열로 변환할 수 있다.  
  
**HTMLCollection**  
getElementsByTagName, getElementsByClassName 메서드가 반환하는 HTMLCollection 객체는 노드 객체의 상태 변화를 실시간으로 반영하는 살아 있는 DOM 컬렉션 객체다. 따라서 HTMLCollection 객체를 **살아 있는 객체**라고 부르기도 한다.  
```html  
<!DOCTYPE html>
<head>
	<style>
		.red { color: red; }
		.blue { color: blue; }
	</style>
</head>
<html>
	<body>
		<ul id="fruits">
			<li class="red">Apple</li>
			<li class="red">Banana</li>
			<li class="red">Orange</li>
		</ul>
		<script>
			// class 값이 'red'인 요소 노드를 모두 탐색하여 HTMLCollection 객체에 담아 반환한다.
			const $elems = document.getElementsByClassName('red');
			// 이 시점에 HTMLCollection 객체에는 3개의 요소 노드가 담겨 있다.
			console.log($elems); // HTMLCollection(3) [li.red, li.red, li.red]
			
			// HTMLCollection 객체의 모든 요소의 class 값을 'blue'로 변경한다.
			for (let i = 0; i < $elems.length; i++) {
				$elems[i].className = 'blue';
			}
			
			// HTMLCollection 객체의 요소가 3개에서 1개로 변경되었다.
			console.log($elems); // HTMLCollection(1) [li.red]
		</script>
	</body>
</html>  
```  
1. **첫 번째 반복**(i === 0) : $elems[0]은 첫 번재 li 요소다. 이 요소는 className 프로퍼티에 의해 값이 ‘blue’로 변경된다. 이때 첫 번째 li 요소는 getElementsByClassName 메서드의 인자로 전달한 ‘red’와 더는 일치하지 않기 때문에 $elems에서 실시간으로 제거된다.   
1. **두 번째 반복**(i === 1) : 첫 번째 반복에서 첫 번째 li 요소는 $elems에서 제거되었다. 따라서 $elems[1]은 세 번째 li 요소다. 이 세 번째 li 요소의 class 값도 ‘blue’로 변경되고 마찬가지로 HTMLCollection인 $elems에서 실시간으로 제외된다.  
1. **세 번째 반복**(i === 2) : 첫 번째, 두 번째 반복에서 첫 번째, 세 번째 li 요소가 $elems에서 제거되었다. 따라서 $elems에는 두 번재 li 요소 노드만 남았다. 이때 $elems.length는 1이므로 for 문의 조건식 i < $elems.length가 false로 평가되어 반복이 종료된다. 따라서 $elems에 남아 있는 두 번째 li 요소의 class 값은 변경되지 않는다.  
이처럼 HTMLCollectioni 객체는 실시간으로 노드 객체의 상태 변경을 반영하여 요소를 제거할 수 있기 때문에 for 문으로 순회하면서 노드 객체의 상태를 변경할 때 주의해야 한다. 이 문제는 for 문을 역방향으로 순회하는 방법으로 회피할 수 있다.  
```javascript  
// for 문을 역방향으로 순회
for (let i = $elems.length - 1; i >= 0; i--) {
	$elems[i].className = 'blue';
}  
```  
또는 while 문을 사용하여 노드 객체가 남아 있지 않을 때까지 무한 반복하는 방법으로 회피할 수 있다.  
```javascript  
// while 문으로 HTMLCollection에 요소가 남아 있지 않을 때까지 무한 반복
let i = 0;
while ($elems.length > i) {
		$elems[i].className = 'blue';
}  
```  
더 간단한 해결책은 부작용을 발생시키는 원인인 HTMLCollection 객체를 사용하지 않는 것이다.  HTMLCollection 객체를 배열로 변환하면 고차 함수(forEach, map, filter, reduce 등)를 사용할 수 있다.  
```javascript  
// 유사 배열 객체이면서 이터러블인 HTMLCollection을 배열로 변환하여 순회
[...$elems].forEach(elem => elem.className = 'blue');  
```  
  
**NodeList**  
HTMLCollection 객체의 부작용을 해결하기 위해 대신 querySelectorAll 메서드를 사용하는 방법도 있다. querySelectorAll 메서드는 DOM 컬렉션 객체인 NodeList 객체를 반환한다. 이때 NodeList 객체는 실시간으로 노드 객체의 상태를 반영하지 않는 객체다.  
```javascript  
// querySelectorAll은 DOM 컬렉션 객체인 NodeList를 반환한다.
const $elems = document.querySelectorAll('.red');

// NodeList 객체는 NodeList.prototype.forEach 메서드를 상속받아 사용할 수 있다.
$elems.forEach(elem => elem.className = 'blue');  
```  
**childNodes 프로퍼티가 반환하는 NodeList 객체는 HTMLCollection 객체와 같이 실시간으로 노드 객체의 상태를 반영하는 live 객체로 동작하므로 주의가 필요한다.**  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="fruits">
			<li>Apple</li>
			<li>Banana</li>
		</ul>
	</body>
	<script>
		const $fruits = document.getElementById('fruits');
		
		// childNodes 프로퍼티는 NodeList 객체(live)를 반환한다.
		const { chlidNodes } = $fruits;
		console.log(childNodes instanceof NodeList); // true
		
		// $fruits 요소의 자식 노드는 공백 텍스트 노드를 포함해 모두 5개다.
		console.log(childNodes); // NodeList(5) [text, li, text, li, text]
		
		for (let i = 0; i < childNodes.length; i++) {
			// removeChild 메서드는 $fruits 요소의 자식 노드를 DOM에서 삭제한다.
			// removeChild 메서드가 호출될 때마다 NodeList 객체인 childNodes가 실시간으로 변경된다.
			// 따라서 첫 번째, 세 번째, 다섯 번째 요소만 삭제된다.
			$fruits.removeChild(childNodes[i]);
		}
		
		// 예상과 다르게 $fruits 요소의 모든 자식 요소가 삭제되지 않는다.
		console.log(childNodes); // NodeList(2) [li, li]
	</script>
</html>  
```  
**노드의 객체의 상태 변경과 상관없이 안전하게 DOM 컬렉션을 사용하려면 HTMLCollection이나 NodeList 객체를 배열로 변환하여 사용하는 것을 권장한다.**  
HTMLCollection과 NodeList 객체는 모두 유사 배열 객체이면서 이터러블이다. 따라서 스프레드 문법이나 Array.from 메서드를 사용하여 간단히 배열로 변환할 수 있다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="fruits">
			<li>Apple</li>
			<li>Banana</li>
		</ul>
	</body>
	<script>
		const $fruits = document.getElementById('fruits');
		
		// childNodes 프로퍼티는 NodeList 객체(live)를 반환한다.
		const { chlidNodes } = $fruits;
		
		// 스프레드 문법을 사용하여 NodeList 객체를 배열로 변환한다.
		[...childNodes].forEach(childNodes => {
			$fruits.removeChild(childNodes);
		});
		
		// $fruits 요소의 모든 자식 노드가 삭제 되었다.
		console.log(childNodes); // NodeList []
	</script>
</html>  
```  
  
---  
## 노드 탐색<br>  
요소 노드를 취득한 다음, 취득한 요소 노드를 기점으로 DOM 트리의 노드를 옮겨 다니며 부모, 형제, 자식 노드 등을 탐색해야 할 때가 있다.  
DOM 트리 상의 노드를 탐색할 수 있도록 Node, Element 인터페이스는 트리 탐색 프로퍼티를 제공한다.  
노드 탐색 프로퍼티는 모두 접근자 프로퍼티지만 getter만 존재하여 참조만 가능한 읽기 전용 프로퍼티다. 값을 할당하면 아무런 에러 없이 무시된다.  
  
### 공백 텍스트 노드<br>  
HTML 요소 사이의 스페이스, 탭, 줄바꿈 등의 공백 문자는 텍스트 노드를 생성한다. 이를 공백 텍스트 노드라 한다.  
  
### 자식 노드 탐색<br>  
|프로퍼티|설명|  
|:---|:---|
|Node.prototype.childNodes|자식 노드를 모두 탐색하여 NodeList에 담아 반환한다. childNodes 프로퍼티가 반환한 NodeList에는 요소 노드뿐만 아니라 텍스트 노드도 포함되어 있을 수 있다.|  
|Element.prototype.children|자식 노드 중에서 요소 노드만 모두 탐색하여 HTMLCollection에 담아 반환한다. children 프로퍼티가 반환한 HTMLCollection에는 텍스트 노드가 포함되지 않는다.|  
|Node.prototype.firstChild|첫 번째 자식 노드를 반환한다. firstChild 프로퍼티가 반환한 노드는 텍스트 노드이거나 요소 노드다.|  
|Node.prottoype.lastChild|마지막 자식 노드를 반환한다. lastChild 프로퍼티가 반환한 노드는 텍스트 노드이거나 요소 노드다.|  
|Element.prototype.firstElementChild|첫 번째 자식 요소 노드를 반환한다. firstElementChild 프로퍼티는 요소 노드만 반환한다.|  
|Element.prototype.lastElementChild|마지막 자식 요소 노드를 반환한다. lastElementChild프로퍼티는 요소 노드만 반환한다.|  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="fruits">
			<li class="apple">Apple</li>
			<li class="banana">Banana</li>
			<li class="orange">Orange</li>
		</ul>
	</body>
	<script>
		// 노드 탐색의 기점이 되는 #fruits 요소 노드를 취득한다.
		const $fruits = document.getElementById('fruits');
		
		// #fruits 요소의 모든 자식 노드를 탐색한다.
		// childNodes 프로퍼티가 반환한 NodeList에는 요소 노드뿐만 아니라 텍스트 노드도 포함되어 있다.
		console.log($fruits.childNodes);
		// NodeList(7) [text, li.apple, text, li.banana, text, li.orange, text]
		
		// #fruits 요소의 모든 자식 노드를 탐색한다.
		// children 프로퍼티가 반환한 HTMLCollection에는 요소 노드만 포함되어 있다.
		console.log($fruits.children);
		// HTMLCollection(3) [li.apple, li.banana, li.orange]
		
		// #fruits 요소의 첫 번째 자식 노드를 탐색한다.
		// firstChild 프로퍼티는 텍스트 노드를 반환할 수도 있다.
		console.log($fruits.firstChild); // #text
		
		// #fruits 요소의 마지막 자식 노드를 탐색한다.
		// lastChild 프로퍼티는 텍스트 노드를 반환할 수도 있다.
		console.log($fruits.lastChild); // #text
		
		// #fruits 요소의 첫 번째 자식 노드를 탐색한다.
		// firstElementChild 프로퍼티는 요소 노드만 반환한다.
		console.log($fruits.firstElementChild); // li.apple
		
		// #fruits 요소의 마지막 자식 노드를 탐색한다.
		// lastElementChild 프로퍼티는 요소 노드만 반환한다.
		console.log($fruits.lastElementChild); // li.orange
	</script>
</html>  
```  
  
### 자식 노드 존재 확인<br>  
Node.prototype.hasChildNodes 메서드는 자식 노드가 존재하면 true, 존재하지 않으면 false를 반환한다. 텍스트 노드를 포함하여 자식 노드의 존재를 확인한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="fruits">
		</ul>
	</body>
	<script>
		// 노드 탐색의 기점이 되는 #fruits 요소 노드를 취득한다.
		const $fruits = document.getElementById('fruits');
		
		// $fruits 요소에 자식 노드가 존재하는지 확인한다.
		// hasChildNodes 메서드는 텍스트 노드를 포함하여 자식 노드의 존재를 확인한다.
		console.log($fruits.hasChildNodes()); // true
	</script>
</html>  
```  
텍스트 노드가 아닌 요소 노드가 존재하는지 확인하려면 childran.length 또는 Element 인터페이스의 childElementCount 프로퍼티를 사용한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="fruits">
		</ul>
	</body>
	<script>
		// 노드 탐색의 기점이 되는 #fruits 요소 노드를 취득한다.
		const $fruits = document.getElementById('fruits');
		
		// hasChildNodes 메서드는 텍스트 노드를 포함하여 자식 노드의 존재를 확인한다.
		console.log($fruits.hasChildNodes()); // true
		
		// 자식 노드 중에 텍스트 노드가 아닌 요소 노드가 존재하는지 확인한다.
		console.log(!!$fruits.children.length); // 0 -> false
		// 자식 노드 중에 텍스트 노드가 아닌 요소 노드가 존재하는지 확인한다.
		console.log(!!$fruits.childElementCount); // 0 -> false
	</script>
</html>  
```  
  
### 요소 노드의 텍스트 노드 탐색<br>  
요소의 텍스트 노드는 firstChild 프로퍼티로 접근할 수 있다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<div id="foo">Hello</div>
		<script>
			// 요소 노드의 텍스트 노드는 firstChild 프로퍼티로 접근할 수 있다.
			console.log(document.getElementById('foo').firstChild); // #text
		</script>
	</body>
</html>  
```  
  
### 부모 노드 탐색<br>  
Node.prototype.parentNode 프로퍼티를 사용한다. 텍스트 노드는 리프 노드이므로 부모 노드가 텍스트 노드인 경우는 없다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="fruits">
			<li class="apple">Apple</li>
			<li class="banana">Banana</li>
			<li class="orange">Orange</li>
		</ul>
	</body>
	<script>
		// 노드 탐색의 기점이 되는 .banana 요소 노드를 취득한다.
		const $banana = document.querySelector('.banana');
		
		// .banana 요소 노드의 부모 노드를 탐색한다.
		console.log($banana.parentNode); // ul#fruits
	</script>
</html>  
```  
  
### 형제 노드 탐색<br>  
어트리뷰트 노드는 요소 노드와 연결되어 있지만 부모 노드가 같은 형제 노드가 아니기 때문에 반환되지 않는다. 즉 아래 프로퍼티는 텍스트 노드 또는 요소 노드만 반환한다.  
|프로퍼티|설명|  
|:---|:---|
|Node.prototype.previousSibling|부모 노드가 같은 형제 노드 중에서 자신의 이전 형제 노드를 탐색하여 반환한다. previousSibling 프로퍼티가 반환하는 형제 노드는 요소 노드뿐만 아니라 텍스트 노드일 수도 있다.|  
|Node.prototype.nextSibling|부모 노드가 같은 형제 노드 중에서 자신의 다음 형제 노드를 탐색하여 반환한다. nextSibling 프로퍼티가 반환하는 형제 노드는 요소 노드뿐만 아니라 텍스트 노드일 수도 있다.|  
|Element.prototype.previousElementSibling|부모 노드가 같은 형제 요소 노드 중에서 자신의 이전 형제 노드를 탐색하여 반환한다. previousElementSibling 프로퍼티는 요소 노드만 반환한다.|  
|Element.prototype.nextElementSibling|부모 노드가 같은 형제 요소 노드 중에서 자신의 다음 형제 노드를 탐색하여 반환한다. nextElementSibling 프로퍼티는 요소 노드만 반환한다.|  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="fruits">
			<li class="apple">Apple</li>
			<li class="banana">Banana</li>
			<li class="orange">Orange</li>
		</ul>
	</body>
	<script>
		// 노드 탐색의 기점이 되는 #fruits 요소 노드를 취득한다.
		const $fruits = document.getElementById('fruits');
		
		// #fruits 요소의 첫 번째 자식 노드를 탐색한다.
		// firstChild 프로퍼티는 요소 노드뿐만 아니라 텍스트 노드를 반환할 수도 있다.
		const { firstChild } = $fruits;
		console.log(firstChild); // #text
		
		// #fruits 요소의 첫 번째 자식 노드의 다음 형제 노드를 탐색한다.
		// nextSibling 프로퍼티는 요소 노드뿐만 아니라 텍스트 노드를 반환할 수도 있다.
		const { nextSibling } = firstChild;
		console.log(nextSibling); // li.apple
		
		// li.apple 요소의 이전 형제 노드를 탐색한다.
		// previousSibling 프로퍼티는 요소 노드뿐만 아니라 텍스트 노드를 반환할 수도 있다.
		const { previousSibling } = nextSibling;
		console.log(previousSibling); // #text
		
		// #fruits 요소의 첫 번째 자식 요소 노드를 탐색한다.
		// firstElementChild 프로퍼티는 요소 노드만 반환한다.
		const { firstElementChild } = $fruits;
		console.log(firstElementChild); // li.apple
		
		// #fruits 요소의 첫 번째 자식 요소 노드의 다음 형제 노드를 탐색한다.
		// nextElementSibling 프로퍼티는 요소 노드만 반환한다.
		const { nextElementSibling } = firstElementChild;
		console.log(nextElementSibling ); // li.banana
		
		// li.banana 요소의 이전 형제 요소 노드를 탐색한다.
		// previousElementSibling 프로퍼티는 요소 노드만 반환한다.
		const { previousElementSibling } = nextElementSibling;
		console.log(previousElementSibling); // li.apple
	</script>
</html>  
```  
  
---  
## 노드 정보 취득<br>  
|프로퍼티|설명|  
|:---|:---|
|Node.prototype.nodeType|노드 객체의 종류, 즉 노드 타입을 나타내는 상수를 반환한다. 노드 타입 상수는 Node에 정의되어 있다.<br>- Node.ELEMENT_NODE: 요소 노드의 타입을 나타내는 상수 1을 반환<br>- Node.TEXT_NODE: 텍스트 노드 타입을 나타내는 상수 3을 반환<br>- Node.DOCUMENT_NODE: 문서 노드 타입을 나타내는 상수 9를 반환|  
|Node.prototype.nodeNmae|노드의 이름을 문자열로 반환한다.<br>- 요소 노드: 대문자 문자열로 태그 이름(”UL”, “LI” 등)을 반환<br>- 텍스트 노드: 문자열 “#text”를 반환<br>- 문서 노드: 문자열 “#document”를 반환|  
```html  
<!DOCTYPE html>
<html>
	<body>
		<div id="foo">Hello</div>
	</body>
	<script>
		// 문서 노드의 노드 정보를 취득한다.
		console.log(document.nodeType); // 9
		console.log(document.nodeName); // #document
		
		// 요소 노드의 노드 정보를 취득한다.
		const $foo = document.getElementById('foo');
		console.log(document.nodeType); // 1
		console.log(document.nodeName); // DIV
		
		// 텍스트 노드의 노드 정보를 취득한다.
		const $textNode = $foo.firstChild;
		console.log(document.nodeType); // 3
		console.log(document.nodeName); // #test
	</script>
</html>  
```  
  
---  
## 요소 노드의 텍스트 조작<br>  
### nodeValue<br>  
Node.prototype.nodeValue 프로퍼티는 setter와 getter 모두 존재하는 프로퍼티다.  
노드 객체의 nodeValue 프로퍼티를 탐조하면 노드 객체의 값을 반환한다. 텍스트 노드가 아닌 요소 노드의 프로퍼티를 참조하면 null을 반환한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<div id="foo">Hello</div>
	</body>
	<script>
		// 문서 노드의 nodeValue 프로퍼티를 참조한다.
		console.log(document.nodeValue); // null
		
		// 요소 노드의 nodeValue 프로퍼티를 참조한다.
		const $foo = document.getElementById('foo');
		console.log($foo.nodeValue); // null
		
		// 텍스트 노드의 nodeValue 프로퍼티를 참조한다.
		const $textNode = $foo.firstChild;
		console.log($textNode.nodeValue); // Hello
	</script>
</html>  
```  
요소 노드의 텍스트를 변경하려면 다음과 같은 순서의 처리가 필요하다.  
1. 텍스트를 변경할 요소 노드를 취득한 다음, 취득한 요소 노드의 텍스트 노드를 탐색한다. 텍스트 노드는 요소 노드의 자식 노드이므로 firstChild 프로퍼티를 사용하여 탐색한다.  
1. 탐색한 텍스트 노드의 nodeValue 프로퍼티를 사용하여 텍스트 노드의 값을 변경한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<div id="foo">Hello</div>
	</body>
	<script>
		// 1. #foo 요소 노드의 자식 노드인 텍스트 노드를 취득한다.
		const $textNode = document.getElementById('foo').firstChild;
		
		// 2. nodeValue 프로퍼티를 사용하여 텍스트 노드의 값을 변경한다.
		$textNode.nodeValue = 'World';
		
		console.log($textNode.nodeValue); // World
	</script>
</html>  
```  
  
### textContent<br>  
Node.prototype.textContent 프로퍼티는 getter와 setter 모두 존재하는 접근자 프로퍼티로서 요소 노드의 텍스트와 모든 자손 노드의 텍스트를 취득하거나 변경한다. 이때 HTML 마크업은 무시된다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<div id="foo">Hello <span>world!</span></div>
	</body>
	<script>
		// #foo 요소 노드의 텍스트를 모두 취득한다. 이때 HTML 마크업은 무시된다.
		console.log(document.getElementById('foo').textContent); // Hello world!
	</script>
</html>  
```  
만약 요소 노드의 콘텐츠 영역에 자식 요소가 노드가 없고 텍스트만 존재한다면 firstChild.nodeValue와 textContent 프로퍼티는 같은 결과를 반환한다. 이 경우 textContent 프로퍼티를 사용하는 편이 코드가 더 간단하다.  
요소 노드의 textContent 프로퍼티에 문자열을 할당하면 요소 노드의 모든 자식 노드가 제거되고 할당한 문자열이 텍스트로 추가된다. 이때 HTML 마크업이 파싱되지 않는다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<div id="foo">Hello <span>world!</span></div>
	</body>
	<script>
		// #foo 요소 노드의 모든 자식 노드가 제거되고 할당한 문자열이 텍스트로 추가된다.
		// 이때 HTML 마크업이 파싱되지 않는다.
		document.getElementById('foo').textContent = 'Hi <span>there!</span>';
	</script>
</html>  
```  
textContent 프로퍼티와 유사한 동작을 하는 innerText 프로퍼티는 다음과 같은 이유로 사용하지 않는 것이 좋다.  
* innerText 프로퍼티는 CSS에 순종적이다. 예를 들어, innerText 프로퍼티는 CSS에 의해 비표시로 지정된 요소 노드의 텍스트를 반환하지 않는다.  
* innerText 프로퍼티는 CSS를 고려해야 하므로 textContent 프로퍼티보다 느리다.  
  
---  
## DOM 조작<br>  
DOM 조작에 의해 DOM에 새로운 노드가 추가되거나 삭제되면 리플로우와 리페인트가 발생하는 원인이 되므로 성능에 영향을 준다. 따라서 복잡한 콘텐츠를 다루는 DOM 조작은 성능 최적화를 위해 주의해서 다뤄야한다.  
### innerHTML<br>  
Element.prototype.innerHTML 프로퍼티는 setter와 getter 모두 존재하는 접근자 프로퍼티로서 요소 노드의 HTML 마크업을 취득하거나 변경한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<div id="foo">Hello <span>world!</span></div>
	</body>
	<script>
		// #foo 요소의 콘텐츠 영역 내의 HTML 마크업을 문자열로 취득한다.
		console.log(document.getElementById('foo').innerHTML);
		// "Hello <span>world!</span>"
	</script>
</html>  
```  
요소 노드의 innerHTML 프로퍼티에 문자열을 할당하면 요소 노드의 모든 자식 노드가 제거되고 할당한 문자열에 포함되어 있는 HTML 마크업이 파싱되어 요소 노드의 자식 노드로 DOM에 반영된다.  
이처럼 innerHTML 프로퍼티를 사용하면 HTML 마크업 문자열로 간단히 DOM 조작이 가능하다.  
하지만 사용자로부터 입력받은 데이터를 그대로 innerHTML 프로퍼티에 할당하는 것은 **크로스 사이트 스크립팅 공격**에 취약하므로 위험하다. HTML 마크업 내에 자바스크립트 악성 코드가 포함되어 있다면 파싱 과정에서 그대로 실행될 가능성이 있기 때문이다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<div id="foo">Hello</div>
	</body>
	<script>
		// innerHTML 프로퍼티로 스크립트 태그를 삽입하여 자바스크립트가 실행되도록 한다.
		// HTML5는 innerHTML 프로퍼티로 삽입된 script 요소 내의 자바스크립트 코드를 실행하지 않는다.
		document.getElementById('foo').innerHTML = '<script>alert(document.cookie)</script>';
	</script>
</html>  
```  
HTML5는 innerHTML 프로퍼티로 삽입된 script 요소 내의 자바스크립트 코드를 실행하지 않는다. 하지만 script 요소 없이도 크로스 사이트 스크립팅 공격은 가능하다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<div id="foo">Hello</div>
	</body>
	<script>
		// 에러 이벤트를 강제로 발생시켜서 자바스크립트 코드가 실행되도록 한다.
		document.getElementById('foo').innerHTML = '<img src="x" onerror="alert(document.cookie)">';
	</script>
</html>  
```  
이처럼 innerHTML 프로퍼티를 이용한 DOM 조작은 구현이 간단하고 직관적이라는 장점이 있지만 크로스 사이트 스크립팅 공격에 취약하다는 단점도 있다.  
또 다른 단점은 요소 노드의 innerHTML 프로퍼티에 HTML 마크업 문자열을 할당하는 경우 요소 노드의 모든 자식 노드를 제거하고 처음부터 새롭게 자식 노드를 생성하여 DOM에 반영한다는 것이다. 이는 효율적이지 않다.  
innerHTML 프로퍼티는 새로운 요소를 삽입할 때 삽입될 위치를 지정할 수 없다는 단점도 있다.  
이처럼 innerHTML 프로퍼티는 복잡하지 않은 요소를 새롭게 추가할 때 유용하지만 기존 요소를 제거하지 않으면서 위치를 지정해 새로운 요소를 삽입해야 할 때는 사용하지 않는 것이 좋다.  
  
### innerAdjacentHTML 메서드<br>  
Element.prototype.insertAdjacentHTML(position, DOMString) 메서드는 기존 요소를 제거하지 않으면서 위치를 지정해 새로운 요소를 삽입한다.  
두 번째 인수로 전달한 HTML 마크업 문자열을 파싱하고 그 결과로 생성된 노드를 첫 번째 인수로 전달한 위치에 삽입하여 DOM에 반영한다. 첫 번째 인수로 전달할 수 있는 문자열은 ‘beforebegin’, ‘afterbegin’, ‘beforeend’, ‘afterend’의 4가지다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<!-- beforebegin -->
		<div id="foo">
			<!-- afterbegin -->
			text
			<!-- beforeend -->
		</div>
		<!-- afterend -->
	</body>
	<script>
			const $foo = document.getElementById('foo');
			
			$foo.insertAdjacentHTML('beforebegin', '<p>beforebegin</p>');
			$foo.insertAdjacentHTML('afterbegin', '<p>afterbegin</p>');
			$foo.insertAdjacentHTML('beforeend', '<p>beforeend</p>');
			$foo.insertAdjacentHTML('afterend', '<p>afterend</p>');
	</script>
</html>  
```  
innerHTML 프로퍼티보다 효율적이고 빠르지만 HTML 마크업 문자열을 파싱하므로 크로스 사이트 스크립팅 공격에 취약하다는 점은 동일하다.  
  
### 노드 생성과 추가<br>  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="fruits">
			<li>Apple</li>
		</ul>
	</body>
	<script>
		const $fruits = document.getElementById('fruits');
		
		// 1. 요소 노드 생성
		const $li = document.createElement('li');
		
		// 2. 텍스트 노드 생성
		const textNode = document.createTextNode('Banana');
		
		// 3. 텍스트 노드를 $li 요소 노드의 자식 노드로 추가
		$li.appendChild(textNode);
		
		// 4. $li 요소 노드를 #fruits 요소 노드의 마지막 자식 노드로 추가
		$fruits.appendChild($li);
	</script>
</html>  
```  
  
**요소 노드 생성**  
Document.prototype.createElement(tagName) 메서드는 요소 노드를 생성하여 반환한다. 매개변수 tagName에는 태그 이름을 나타내는 문자열을 인수로 전달한다.  
```javascript  
// 1. 요소 노드 생성
const $li = document.createElement('li');  
```  
createElement 메서드는 요소 노드를 생성할 뿐 DOM에 추가하지 않는다. 생성한 요소 노드는 아무런 자식 노드를 가지고 있지 않다. 따라서 요소 노드의 자식 노드인 텍스트 노드도 없는 상태다.  
```javascript  
// 1. 요소 노드 생성
const $li = document.createElement('li');
// 생성된 요소 노드에는 아무런 자식 노드가 없다.
console.log($li.childNodes); // NodeList []  
```  
  
**텍스트 노드 생성**  
Document.prototype.createTextNode(text) 메서드는 텍스트 노드를 생성하여 반환한다. 매개변수 text에는 텍스트 노드의 값으로 사용할 문자열을 인수로 전달한다.  
```javascript  
// 2. 텍스트 노드 생성
const textNode = document.createTextNode('Banana');  
```  
createElement 메서드와 마찬가지로 createTextNode 메서드는 텍스트 노드를 생성할 뿐 요소 노드에 추가하지 않는다.   
  
**텍스트 노드를 요소 노드의 자식 노드로 추가**  
Node.prototype.appendChild(childNode) 메서드는 매개변수 childNode에게 인수로 전달한 노드를 appendChild 메서드를 호출한 노드의 마지막 자식 노드로 추가한다.  
```javascript  
// 3. 텍스트 노드를 $li 요소 노드의 자식 노드로 추가
$li.appendChild(textNode);  
```  
위 예제처럼 요소 노드에 자식 노드가 하나도 없는 경우에는 텍스트 노드를 생성하여 요소 노드의 자식 노드로 텍스트를 추가하는 것보다 textContent 프로퍼티를 사용하는 편이 더욱 간편하다.  
```javascript  
// 텍스트 노드를 요소 노드의 자식 노드로 추가
$li.appendChild(document.createTextNode('Banana'));

// $li 요소 노드에 자식 노드가 하나도 없는 위 코드와 동일하게 동작한다.
$li.textContent = 'Banana';  
```  
  
**요소 노드를 DOM에 추가**  
Node.prototype.appendChild 메서드를 사용하여 텍스트 노드와 부자 관계로 연결한 요소 노드를 #fruits 요소 노드의 마지막 자식 요소로 추가한다.  
```javascript  
// 4. $li 요소 노드를 #fruits 요소 노드의 마지막 자식 노드로 추가
$fruits.appendChild($li);  
```  
위 예제는 단 하나의 요소 노드를 생성하여 DOM에 한 번 추가하므로 DOM은 한 번 변경된다. 이때 리플로우와 리페인트가 실행된다.  
  
### 복수의 노드 생성과 추가<br>  
Document.prototype.createDocumentFragment 메서드는 비어 있는 DocumentFragment 노드를 생성하여 반환한다.  
DocumentFragment 노드는 문서, 요소, 어트리뷰트, 텍스트 노드와 같은 노드 객체의 일종으로, 부모 노드가 없어서 기존 DOM과는 별도로 존재한다는 특징이 있다. 자식 노드들의 부모 노드로서 별도의 서브 DOM을 구성하여 기존 DOM에 추가하기 위한 용도로 사용된다.  
DocumentFragment 노드를 DOM에 추가하면 자신은 제거되고 자신의 자식 노드만 DOM에 추가된다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="fruits"></ul>
	</body>
	<script>
		const $fruits = document.getElementById('fruits');
		
		// DocumentFragment 노드 생성
		const $fragment = document.createDocumentFragment();
		
		['Apple', 'Banana', 'Orange'].forEach(text => {
			// 1. 요소 노드 생성
			const $li = document.createElement('li');
			
			// 2. 텍스트 노드 생성
			const textNode = document.createTextNode(text);
			
			// 3. 텍스트 노드를 $li 요소 노드의 자식 노드로 추가
			$li.appendChild(textNode);
			
			// 4. $li 요소 노드를 DocumentFragment 요소 노드의 마지막 자식 노드로 추가
			$fragment.appendChild($li);
		});
		
		// 5. DocumentFragment 노드를 #fruits 요소 노드의 마지막 자식 노드로 추가
		$fruits.appendChild($fragment);
	</script>
</html>  
```  
실제로 DOM 변경이 발생하는 것은 한 번 뿐이므로 리플로우와 리페인트도 한 번만 실행된다.   
  
### 노드 삽입<br>  
**마지막 노드로 추가**  
Node.prototype.appendChild 메서드는 인수로 전달받은 노드를 자신을 호출한 노드의 마지막 자식 노드로 DOM에 추가한다. 이때 노드를 추가할 위치를 지정할 수 없고 언제나 마지막 자식 노드로 추가한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="fruits">
			<li>Apple</li>
			<li>Banana</li>
		</ul>
	</body>
	<script>
		// 요소 노드 생성
		const $li = document.createElement('li');
	
		// 텍스트 노드를 $li 요소 노드의 자식 노드로 추가
		$li.appendChild(document.createTextNode('Orange'));
		
		// $li 요소 노드를 #fruits요소 노드의 마지막 자식 노드로 추가
		document.getElementById('fruits').appendChild($li);
	</script>
</html>  
```  
  
**지정한 위치에 노드 삽입**  
Node.prototype.insertBefore(newNode, childNode) 메서드는 첫 번째 인수로 전달받은 노드를 두 번째 인수로 전달받은 노드 앞에 삽입한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="fruits">
			<li>Apple</li>
			<li>Banana</li>
		</ul>
	</body>
	<script>
		const $fruits = document.getElementById('fruits');
		
		// 요소 노드 생성
		const $li = document.createElement('li');
	
		// 텍스트 노드를 $li 요소 노드의 자식 노드로 추가
		$li.appendChild(document.createTextNode('Orange'));
		
		// $li 요소 노드를 #fruits요소 노드의 마지막 자식 요소 앞에 삽입
		$fruits.insertBefore($li, $fruits.lastElementChild);
		// Apple - Orange - Banana
	</script>
</html>  
```  
두 번재 인수로 전달받은 노드는 반드시 insesrtBefore 메서드를 호출한 노드의 자식 노드여야 한다. 그렇지 않으면 DOMException 에러가 발생한다.  
두 번재 인수로 전달받은 노드가 null이면 appendChild 메서드처럼 동작한다.  
  
### 노드 이동<br>  
DOM에 이미 존재하는 노드를 appendChild 또는 insertBefore 메서드를 사용하여 DOM에 다시 추가하면 현재 위치에서 노드를 제거하고 새로운 위치에 노드를 추가한다. 즉, 노드가 이동한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="fruits">
			<li>Apple</li>
			<li>Banana</li>
			<li>Orange</li>
		</ul>
	</body>
	<script>
		const $fruits = document.getElementById('fruits');
		
		// 이미 존재하는 요소 노드를 취득
		const [$apple, $banana, ] = $fruits.children;
	
		// 이미 존재하는 $apple 요소 노드를 #fruits 요소 노드의 마지막 노드로 이동
		$fruits.appendChild($apple); // Banana - Orange - Apple
		
		// 이미 존재하는 $banana 요소 노드를 #fruits 요소 노드의 마지막 자식 노드 앞으로 이동
		$fruits.insertBefore($banana, $fruits.lastElementChild); 
		// Orange - Banana - Apple
	</script>
</html>  
```  
  
### 노드 복사<br>  
Node.prototype.cloneNode([deep: true | false]) 메서드는 노드의 사본을 생성하여 반환한다. 매개변수 deep에 true를 인수로 전달하면 노드를 깊은 복사(deep copy)하여 모든 자손 노드가 포함된 사본을 생성하여, false를 인수로 전달하거나 생략하면 노드를 얕은 복사(shallow copy)하여 노드 자신만을 사본으로 생성한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="fruits">
			<li>Apple</li>
		</ul>
	</body>
	<script>
		const $fruits = document.getElementById('fruits');
		const $apple = $fruits.firstElementChild;
		
		// $apple 요소를 얕은 복사하여 사본을 생성. 텍스트 노드가 없는 사본이 생성된다.
		const $shallowClone = $apple.cloneNode();
		// 사본 요소 노드에 텍스트 추가
		$shallowClone.textContent = 'Banana';
		// 사본 요소 노드를 #fruits 요소 노드의 마지막 노드로 추가
		$fruits.appendChild($shallowClone);
		
		// #fruits 요소를 깊은 복사하여 모든 자손 노드가 포함된 사본을 생성
		const $deepClone = $fruits.cloneNode(true);
		// 사본 요소 노드를 #fruits 요소 노드의 마지막 노드로 추가
		$fruits.appendChild($deepClone);
	</script>
</html>  
```  
  
### 노드 교체<br>  
Node.prototype.replaceChild(newChild, oldChild) 메서드는 자신을 호출한 노드의 자식 노드인 oldChild 노드를 newChild 노드로 교체한다. 이때 oldChild 노드는 DOM에서 제거된다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="fruits">
			<li>Apple</li>
		</ul>
	</body>
	<script>
		const $fruits = document.getElementById('fruits');
		
		// 기존 노드와 교체할 요소 노드를 작성
		const $newChild = document.createElement('li');
		$newChild.textContent = 'Banana';
		
		// #fruits 요소 노드의 첫 번째 자식 요소 노드를 $newChild 요소 노드로 교체
		$fruits.replaceChild($newChild, $fruits.firstElementChild);
	</script>
</html>  
```  
  
### 노드 삭제<br>  
Node.prototype.removeChild(child) 메서드는 child 매개변수에 인수로 전달한 노드를 DOM에서 삭제한다. 인수로 전달한 노드는 removeChild 메서드를 호출한 노드의 자식 노드이어야 한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="fruits">
			<li>Apple</li>
			<li>Banana</li>
		</ul>
	</body>
	<script>
		const $fruits = document.getElementById('fruits');
	
		// #fruits 요소 노드의 마지막 요소를 DOM에서 삭제
		$fruits.replaceChild($fruits.lastElementChild);
	</script>
</html>  
```  
  
---  
## 어트리뷰트<br>  
### 어트리뷰트 노드와 attributes 프로퍼티<br>  
HTML 문서가 파싱될 때 HTML 요소의 어트리뷰트는 어트리뷰트 노드로 변환되어 요소 노드와 연결된다. 이때 HTML 어트리뷰트당 하나의 어트리뷰트 노드가 생성된다.  
모든 어트리뷰트 노드의 참조는 유사 배열 객체이자 이터러블인 NameNodeMap 객체에 담겨서 요소 노드의 attribute 프로퍼티에 저장된다.  
따라서 요소 노드의 모든 어트리트뷰트 노드는 요소 노드의 Element.prototype.attributes 프로퍼티로 취득할 수 있다. attributes 프로퍼티는 getter만 존재하는 읽기 전용 접근자 프로퍼티다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<input id="user" type="text" value="ungmo2">
		<script>
			// 요소 노드의 attribute 프로퍼티는 요소 노드의 모든 어트리뷰트 노드의 참조가 담긴
			// NameNodeMap 객체를 반환한다.
			const { attributes } = document.getElementById('user');
			console.log(attributes);
			// NameNodeMap {0: id, 1: type, 2: value, id: id, type: type, value: value, length: 3}
			
			// 어트리뷰트 값 취득
			console.log(attributes.id.value); // user
			console.log(attributes.type.value); // text
			console.log(attributes.value.value); // ungmo2
		</script>
	</body>
</html>  
```  
  
### HTML 어트리뷰트 조작<br>  
Element.prototype.getAttribute/setAttribute 메서드를 사용하면 attributes 프로퍼티를 통하지 않고 요소 노드에서 메서드를 통해 직접 HTML 어트리뷰트 값을 취득하거나 변경할 수 있다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<input id="user" type="text" value="ungmo2">
		<script>
			const $input = document.getElementById('user');
			
			// value 어트리뷰트 값을 취득
			const inputValue = $input.getAttribute('value');
			console.log(inputValue); // ungmo2
			
			// value 어트리뷰트 값을 변경
			$input.setAttribute('value', 'foo');
			console.log($input.getAttribute('value')); // foo
		</script>
	</body>
</html>  
```  
특정 어트리뷰트가 존재하는지 확인하려면 Element.prototype.hasAttribute(attributeName) 메서드를 사용하고, 특정 HTML 어트리뷰트를 삭제하려면 Element.prototype.removeAttribute(attributeName) 메서드를 사용한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<input id="user" type="text" value="ungmo2">
		<script>
			const $input = document.getElementById('user');
			
			// value 어트리뷰트 존재 확인
			if ($input.hasAttribute('value')) {
				// value 어트리뷰트 삭제
				$input.removeAttribute('value');
			}
			
			// value 어트리뷰트가 삭제되었다.
			console.log($input.hasAttribute('value')); // false
		</script>
	</body>
</html>  
```  
  
### HTML 어트리뷰트 vs. DOM 프로퍼티<br>  
요소 노드 객체에는 HTML 어트리뷰트에 대응하는 프로퍼티(이하 DOM 프로퍼티)가 존재한다. 이 DOM 프로퍼티들을 HTML 어트리뷰트 값을 초기값으로 가지고 있다.  
**요소 노드는 2개의 상태, 초기 상태와 최신 상태를 관리해야 한다. 요소 노드의 초기 상태는 어트리뷰트 노드가 관리하며, 요소 노드의 최신 상태는 DOM 프로퍼티가 관리한다.**  
  
**어트리뷰트 노드**  
**HTML 어트리뷰트로 지정한 HTML 요소의 초기 상태는 어트리뷰트 노드에서 관리한다. **  
어트리뷰트 노드가 관리하는 초기 상태 값을 취득하거나 변경하려면 getAttribute/setAttribute 메서드를 사용한다. getAttribute 메서드로 취득한 값은 초기 상태 값이다. HTML 요소에 지정한 어트리뷰트 값은 사용자의 입력에 의해 상태가 변경되어도 변하지 않으므로 결과는 언제나 동일하다.  
setAttribute 메서드는 어트리뷰트 노드에서 관리하는 HTML 요소에 지정한 어트리뷰트 값, 즉 초기 상태값을 변경한다.  
  
**DOM 프로퍼티**  
사용자가 입력한 최신 상태는 HTML 어트리뷰트에 대응하는 요소 노드의 DOM 프로퍼티가 관리한다. DOM 프로퍼티는 사용자의 입력에 의한 상태 변화에 반응하여 언제나 최신 상태를 유지한다.  
이에 반해, getAttribute 메서드로 취득한 HTML 어트리뷰트 값은 변하지 않고 유지된다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<input id="user" type="text" value="ungmo2">
		<script>
			const $input = document.getElementById('user');
			
			// 사용자가 input 요소의 입력 필드에 값을 입력할 때마다 input 요소 노드의 value 프로퍼티 값,
			// 즉 최신 상태 값을 취득한다. value 프로퍼티 값은 사용자의 입력에 의해 동적으로 변경된다.
			$input.oninput = () => {
				console.log('value 프로퍼티 값', $input.value);
			};
			
			// getAttribute 메서드로 취득한 HTML 어트리뷰트 값, 즉 초기 상태 값은 변하지 않고 유지된다.
			console.log('value 어트리뷰트 값', $input.getAttribute('value'));
		</script>
	</body>
</html>  
```  
사용자가 상태를 변경하는 행위는 HTML 요소에 지정한 어트리뷰트 값에는 어떠한 영향도 주지 않는다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<input id="user" type="text" value="ungmo2">
		<script>
			const $input = document.getElementById('user');
			
			// DOM 프로퍼티에 값을 할당하여 HTML 요소의 최신 상태를 변경한다.
			$input.value = 'foo';
			console.log($input.value); // foo
			
			// getAttribute 메서드로 취득한 HTML 어트리뷰트 값, 즉 초기 상태 값은 변하지 않고 유지된다.
			console.log($input.getAttribute('value')); // ungmo2
		</script>
	</body>
</html>  
```  
사용자 입력에 의한 상태 변화와 관계있는 DOM 프로퍼티만 최신 상태 값을 관리한다. 그 외의 사용자 입력에 의한 상태 변화와 관계없는(예: id) 어트리뷰트와 DOM 프로퍼티는 항상 동일한 값으로 연동한다.  
  
**HTML 어트리뷰트와 DOM 프로퍼티의 대응 관계**  
HTML 어트리뷰트와 DOM 프로퍼티는 언제나 1:1로 대응하는 것은 아니며 HTML 어트리뷰트 이름과 DOM 프로퍼티 키가 반드시 일치하는 것도 아니다.  
* id 어트리뷰트와 id 프로퍼티는 1:1 대응하며, 동일한 값으로 연동한다.  
* input 요소의 value 어트리뷰트는 value 프로퍼티와 1:1 대응하나. 하지만 value 어트리뷰트는 초기 상태를, value 프로퍼티는 최신 상태를 갖는다.  
* class 어트리뷰트는 className, classList 프로퍼티와 대응한다.  
* for 어트리뷰트는 htmlFor 프로퍼티와 1:1 대응한다.  
* td 요소의 colspan 어트리뷰트는 대응하는 프로퍼티가 존재하지 않는다.  
* textContent 프로퍼티는 대응하는 어트리뷰트가 존재하지 않는다.  
* 어트리뷰트 이름은 대소문자를 구별하지 않지만 대응하는 프로퍼티 키는 카멜 케이스를 따른다.  
  
**DOM 프로퍼티 값의 타입**  
getAttribute 메서드로 취득한 어트리뷰트 값은 언제나 문자열이다. 하지만 DOM 프로퍼티로 취득한 최신 상태 값은 문자열이 아닐 수도 있다. 예를 들어, checkbox 요소의 checked 어트리뷰트의 값은 문자열이지만 checked 프로퍼티 값은 불리언 타입이다.  
  
### data 어트리뷰트와 dataset 프로퍼티<br>  
data 어트리뷰트와 dataset 프로퍼티를 사용하면 HTML 요소에 정의한 사용자 정의 어트리뷰트와 자바스크립트 간에 데이터를 교환할 수 있다. data 어트리뷰트는 data- 접두사 다음에 임의의 이름을 붙여 사용한다.  
data 어트리뷰트 값은 HTMLElement.dataset 프로퍼티로 취득하거나 변경할 수 있다. dataset 프로퍼티는 HTML 요소의 모든 data 어트리뷰트의 정보를 제공하는 DOMStringMap 객체를 반환한다.   
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="users">
			<li id="1" data-user-id="7621" data-role="admin">Lee</li>
			<li id="2" data-user-id="9524" data-role="subscriber">Kim</li>
		</ul>
		<script>
			const users = [...document.quertySelector('.users').children];
			
			// user-id가 '7621'인 요소 노드를 취득한다.
			const user = users.find(user => user.dataset.userId === '7621');
			// user-id가 '7621'인 요소 노드에서 data-role의 값을 취득한다.
			console.log(user.dataset.role); // "admin"
			
			// user-id가 '7621'인 요소 노드에서 data-role의 값을 변경한다.
			user.dataset.role = 'subscriber';
			// dataset 프로퍼티는 DOMStringMap 객체를 반환한다.
			console.log(user.dataset); // DOMStringMap {userId: "7621", role: "subscriber"}
		</script>
	</body>
</html>  
```  
data 어트리뷰트의 data- 접두사 다음에 존재하지 않는 이름을 키로 사용하여 dataset 프로퍼티에 값을 할당하면 HTML 요소에 data 어트리뷰트가 추가된다. 이때 dataset 프로퍼티에 추가한 카멜케이스의 프로퍼티 키는 data 어트리뷰트의 data- 접두사 다음에 케밥케이스로 자동 변경되어 추가한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<ul id="users">
			<li id="1" data-user-id="7621">Lee</li>
			<li id="2" data-user-id="9524">Kim</li>
		</ul>
		<script>
			const users = [...document.quertySelector('.users').children];
			
			// user-id가 '7621'인 요소 노드를 취득한다.
			const user = users.find(user => user.dataset.userId === '7621');

			// user-id가 '7621'인 요소 노드에 새로운 data 어트리뷰트를 추가한다.
			user.dataset.role = 'admin';
			console.log(user.dataset); 
			/*
			DOMStringMap {userId: "7621", role: "admin"}
			-> <li id="1" data-user-id="7621" data-role="admin">Lee</li>
			*/
		</script>
	</body>
</html>  
```  
  
---  
## 스타일<br>  
### 인라인 스타일 조작<br>  
HTMLElement.prototype.style 프로퍼티는 setter와 getter 모두 존재하는 접근자 프로퍼티로서 요소 노드의 **인라인 스타일**을 취득하거나 추가 또는 변경한다.  
```html  
<!DOCTYPE html>
<html>
	<body>
		<div style="color: red">Hello World</div>
		<script>
			const $div = document.querySelector('div');
			
			// 인라인 스타일 취득
			console.log($div.style); // CSSStyleDeclaration { 0: "color", ... }
			
			// 인라인 스타일 변경
			$div.style.color = 'blue';
			
			// 인라인 스타일 추가
			$div.style.width = '100px';
			$div.style.height = '100px';
			$div.style.backgroundColor = 'yellow';
		</script>
	</body>
</html>  
```  
CSS 프로퍼티는 케밥 케이스를 따른다. 이에 대응하는 CSSStyleDeclaration 객체의 프로퍼티는 카멜 케이스를 따른다.   
케밥 케이스의 CSS 프로퍼티를 그대로 사용하려면 객체의 마침표 표기법 대신 대괄호 표기법을 사용한다.  
```javascript  
$div.style['background-color'] = 'yellow';  
```  
단위 지정이 필요한 CSS 프로퍼티 값은 반드시 단위를 지정해야 한다.  
  
### 클래스 조작<br>  
class 어트리뷰트에 대응하는 DOM 프로퍼티는 class가 아니라 className과 classList다. 자바스크립트에서 class는 예약어이기 때문이다.  
  
**className**  
Element.prototype.className 프로퍼티는 setter와 getter 모두 존재하는 접근자 프로퍼티로서 HTML 요소의 class 어트리뷰트 값을 취득하거나 변경한다.  
요소 노드의 className 프로퍼티를 참조하면 class 어트리뷰트 값을 문자열로 반환하고, 요소 노드의 className 프로퍼티에 문자열을 할당하면 class 어트리뷰트 값을 할당한 문자열로 변경한다.  
```html  
<!DOCTYPE html>
<html>
	<head>
		<style>
			.box {
				width: 100px; height: 100px;
				background-color: antiquewhite;
			}
			.red { color: red; }
			.blue { color: blue; }
		</style>
	</head>
	<body>
		<div class="box red">Hello World</div>
		<script>
			const $box = document.querySelector('.box');
			
			// .box 요소의 class 어트리뷰트 값을 취득
			console.log($box.className); // 'box red'
			
			// .box 요소의 class 어트리뷰트 값 중에서 'red'만 'blue'로 변경
			$box.className = $box.className.replace('red', 'blue'); 
		</script>
	</body>
</html>  
```  
className 프로퍼티는 문자열을 반환하므로 공백으로 구분된 여러 개의 클래스를 반환하는 경우 다루기가 불편하다.  
  
**classList**  
Element.prototype.classList 프로퍼티는 class 어트리뷰트의 정보를 담은 DOMTokenList 객체를 반환한다.  
```html  
<!DOCTYPE html>
<html>
	<head>
		<style>
			.box {
				width: 100px; height: 100px;
				background-color: antiquewhite;
			}
			.red { color: red; }
			.blue { color: blue; }
		</style>
	</head>
	<body>
		<div class="box red">Hello World</div>
		<script>
			const $box = document.querySelector('.box');
			
			// .box 요소의 class 어트리뷰트 정보를 담은 DOMTokenList 객체를 취득
			// classList가 반환하는 DOMTokenList 객체는 HTMLCollection과 NodeList와 같이
			// 노드 객체의 상태 변화를 실시간으로 반영하는 살아있는 객체다.
			console.log($box.classList); /
			// DOMTokenList(2) [length: 2, value: "box blue", 0: "box", 1: "blue"]
			
			// .box 요소의 class 어트리뷰트 값 중에서 'red'만 'blue'로 변경
			$box.classList.replace('red', 'blue'); 
		</script>
	</body>
</html>  
```  
DOMTokenList 객체는 유사 배열 객체이면서 이터러블이다. DOMTokenList 객체는 다음과 같이 유용한 메서드들을 제공한다.  
* **add(…className) **: add 메서드는 인수로 전달한 1개 이상의 문자열을 class 어트리뷰트 값으로 추가한다.  
```javascript  
$box.classList.add('foo'); // -> class="box red foo"
$box.classList.add('bar', 'baz'); // -> class="box red foo bar baz"  
```  
* **remove(…className)** : remove 메서드느느 인수로 전달한 1개 이상의 문자열과 일치하는 클래스를 class 어트리뷰트에서 삭제한다. 인수로 전달한 문자열과 일치하는 클래스가 없으면 에러없이 무시된다.  
```javascript  
$box.classList.remove('foo'); // -> class="box red bar baz"
$box.classList.remove('bar', 'baz'); // -> class="box red"
$box.classList.remove('x'); // -> class="box red"  
```  
* **item(index)** : item 메서드는 인수로 전달한 index에 해당하는 클래스를 class 어트리뷰트에서 반환한다.   
```javascript  
$box.classList.item(0); // -> "box"
$box.classList.item(1); // -> "red"  
```  
* contains(className) : contains 메서드는 인수로 전달한 문자열과 일치하는 클래스가 class 어트리뷰트에 포함되어 있는지 확인한다.  
```javascript  
$box.classList.contains('box'); // -> true
$box.classList.contains('blue'); // -> false  
```  
* replace(oldClassName, newClassName) : replace 메서드는 class 어트리뷰트에서 첫 번째 인수로 전달한 문자열을 두 번째 인수로 전잘한 문자열로 변경한다.  
```javascript  
$box.classList.replace('red', 'blue'); // -> class="box blue"  
```  
* toggle(className[, force]) : toggle 메서드는 class 어트리뷰트에 인수로 전달한 문자열과 일치하는 클래스가 존재하면 제거하고, 존재하지 않으면 추가한다. 두 번째 인수로 불리언 값으로 평가되는 조건식을 전달할 수 있다. 이때 조건식의 평가 결과가 true 이면 class 어트리뷰트에 강제로 문자열을 추가하고 false이면 강제로 제거한다.  
```javascript  
$box.classList.toggle('foo'); // -> class="box blue foo"
$box.classList.toggle('foo'); // -> class="box blue"

// class 어트리뷰트에 강제로 'foo' 클래스 추가
$box.classList.toggle('foo', true); // -> class="box blue foo"
// class 어트리뷰트에서 강제로 'foo' 클래스 제거
$box.classList.toggle('foo', false); // -> class="box blue"  
```  
  
### 요소에 적용되어 있는 CSS 스타일 참조<br>  
window.getComputedStyle(element[, pseudo]) 메서드는 첫 번째 인수로 전달한 요소 노드에 적용되어 있는 평가된 스타일을 CSSStyleDeclaration 객체에 담아 반환한다. 평가된 스타일이란 요소에 적용되어 있는 모든 스타일(링크 스타일, 임베딩 스타일, 인라인 스타일, 자바스크립트에서 적용한 스타일, 상속된 스타일, 기본 스타일 등)이 조합되어 최종적으로 적용된 스타일을 말한다.  
```html  
<!DOCTYPE html>
<html>
	<head>
		<style>
			body {
				color: red;
			}
			.box {
				width: 100px; 
				height: 50px;
				background-color: cornsilk;
				border: 1px solid black;
			}
		</style>
	</head>
	<body>
		<div class="box">Box</div>
		<script>
			const $box = document.querySelector('.box');
			
			// .box 요소에 적용된 모든 CSS 스타일을 담고 있는 CSSStyleDeclaration 객체를 취득
			const computedStyle = window.getComputedStyle($box);
			console.log(computedStyle); // CSSStyleDeclaration 
			
			// 임베딩 스타일
			console.log(computedStyle.width); // 100px
			console.log(computedStyle.height); // 50px
			console.log(computedStyle.backgroundColor); // rgb(255, 248, 220)
			console.log(computedStyle.border); // 1px solid rgb(0, 0, 0)
			
			// 상속 스타일(body -> .box)
			console.log(computedStyle.color); // rgb(255, 0, 0)
			
			// 기본 스타일
			console.log(computedStyle.display); // block
		</script>
	</body>
</html>  
```  
getComputedStyle 메서드의 두 번째 인수로 :after, :before와 같은 의사 요소를 지정하는 문자열을 전달할 수 있다. 의사 요소가 아닌 일반 요소의 경우 생략한다.  
```html  
<!DOCTYPE html>
<html>
	<head>
		<style>
			.box:before {
				content: 'Hello';
			}
		</style>
	</head>
	<body>
		<div class="box">Box</div>
		<script>
			const $box = document.querySelector('.box');
			
			// 의사 요소 :before의 스타일을 취득한다.
			const computedStyle = window.getComputedStyle($box, ':before');
			console.log(computedStyle.content); // "Hello"
		</script>
	</body>
</html>  
```  
  
---  
## DOM 표준<br>  
DOM은 현재 다음과 같이 4개의 레벨(버전)이 있다.  
|레벨|표준 문서 URL|  
|:---|:---|
|DOM Level1|https://www.w3.org/TR/REC-DOM-Level-1|  
|DOM Level2|https://www.w3.org/TR/DOM-Level-2-Core|  
|DOM Level3|https://www.w3.org/TR/DOM-Level-3-Core|  
|DOM Level4|https://dom.spec.whatwg.org|  
  
