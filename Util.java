package BankClientUp;

//
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Util {
	Scanner sc = new Scanner(System.in);

	public String passwordDoChk(Map<String, ClientDTO> cmap) {
		System.out.print(" password  >  ");
		String password = sc.next();
		while (true) {
			System.out.print(" password chk!! > ");
			String passwordchk = sc.next();
			if (password.equals(passwordchk)) {
				return password;
			}
		}
	}

	public String idDoChk(Map<String, ClientDTO> cmap) {// id 중복 체크 메소드
		List<String> keys = new ArrayList<>(cmap.keySet());
		Collections.sort(keys);
		while (true) {
			System.out.print(" id  >  ");
			String id = sc.next();
			boolean find = false;
			if (cmap.size() == 0) {
				System.out.println("yor are first member");
				return id;
			} else {
				System.out.println("map size is not 0");
				for (String c : keys) {
					if (cmap.get(c).getId().equals(id)) {
						System.out.println("중복된 아이디 입니다. ");
						find = true;
						break;
					}
				}
				if (find) {
					continue;
				}
				System.out.println("최고의 아이디네요~~~!!!!. ");
				return id;
			}
		}
	}
	public int numberCheck() {
		if (sc.hasNextInt()) {
			return sc.nextInt();
		} else {
			sc.nextLine();
			return -1;
		}
	}
}
/*
 * !!!설계 이상한 부분이나 잘못된 부분이나 추가되면 좋을 것 같은 부분이 있다면 알려주세요!!! !!!세부 알고리즘 설계는 알아서 할
 * 것!!! !!!첫번째로 list로 만들고 두번째로 Map으로 만들거나 list를 Map으로 변경!!!
 * 
 * 
 * 로그인되지 않았을 때 메뉴 : 1.회원가입 2.로그인 3.리스트 4.검색 0.종료 !!기능!! 1. util클래스를 만들어 동일한 아이디가
 * 회원가입되지 않게 해야함 - 받는 값 : 아이디, 패스워드, 패스워드확인, 이름, 목적(1.예적금, 2.투자, 3.월급, 4.생활비) -
 * 계좌생성은 아이디로 로그인하여 계좌를 만들어야 하는 것으로 생성자 실행시 계좌과 자동으로 들어가면 안 됨 2. 회원가입되어 있는 아이디와
 * 패스워드가 동일하면 로그인됨 - 받는 값 : 아이디, 패스워드 3. 회원가입되어 있는 모든 회원의 리스트 출력 4. 이름이나 목적 두가지로
 * 검색 가능 - 받는 값 : 이름검색이면 이름 / 목적검색이면 목적 - 조회한 이름이나 목적으로 회원가입한 회원이 없을 경우 조회할 내역이
 * 없다는 문구 출력 0. 종료됨
 * 
 * 로그인되었을 때 메뉴 : 1.입출금조회 2.입금 3.출금 4.계좌이체 5.계좌추가 6.계좌리스트 7.패스워드변경 8.로그아웃 9.회원탈퇴
 * 0.종료 !!기능!! 1. 회원은 여러 계좌를 가질 수 있기 때문에 조회할 계좌를 입력하여 해당 계좌의 입금, 출금 내역이 출력됨 - 받는
 * 값 : 입금할 본인계좌 - 조회할 계좌를 입력했을 때 조회할 계좌가 없으면 계좌 없다는 문구 출력 - 조회할 계좌가 있으나 입출금 내역이
 * 없으면 계좌 정보만 뜨고 계좌정보 하단에는 입출금 내역이 없다는 문구 출력 - 조회될 계좌도 있고 해당 계좌에 입출금 내역도 있으면 상단에
 * 계좌 정보와 하단에 해당 계좌의 입출금 내역이 출력 2. 해당 아이디가 가지고 있는 입금할 계좌를 입력하여 해당 계좌에서 입금 - 받는 값
 * : 출금할 본인계좌 - 입금할 계좌가 해당 아이디의 계좌가 아니면 해당 아이디의 계좌가 아니라서 입금이 어렵다는 문구 출력 - 해당 계좌의
 * 거래내역에 추가되어야 함 3. 해당 아이디가 가지고 있는 출금할 계좌를 입력하여 해당 계좌에서 출금 - 받는 값 : 출금할 본인계좌 -
 * 출금할 계좌가 해당 아이디의 계좌가 아니면 해당 아이디의 계좌가 아니라서 출금이 어렵다는 문구 출력 - 해당 계좌의 거래내역에 추가되어야
 * 함 4. 해당 아이디가 가지고 있는 출금 계좌를 입력하고 계좌이체할 계좌를 입력하여 출금계좌에서는 출금을 이체계좌에서는 입금을 진행 -
 * 받는 값 : 출금할 본인계좌, 이체할 계좌 - 출금할 계좌가 해당 아이디의 계좌가 아니면 해당 아이디의 계좌가 아니라서 출금이 어렵다는
 * 문구 출력 - 출금할 계좌의 잔액이 이체할 금액보다 작다면 잔액부족이라는 문구가 출력되고 출금과 입금이 모두 진행되지 않음 - 이체할
 * 계좌가 없는 계좌번호라면 이체할 계좌번호가 없다는 문구 출력 - 해당 계좌의 거래내역에 추가되어야 함 5. 계좌추가 입력시 로그인한
 * 아이디의 계좌가 생성됨 - 계좌번호는 자동생성 - 로그인한 회원의 아이디와 패스워드, 이름, 목적이 자동으로 셋팅되도록 만들어야 함 6.
 * 로그인한 아이디가 가지고 있는 계좌가 모두 출력 - 계좌가 없으면 계좌가 없으니 계좌를 생성하라는 문구 출력 7. 패스워드를 확인을 위해
 * 패스워드를 입력받고 로그인된 패스워드와 동일한지 확인후 변경됨 - 받는 값 : 패스워드 확인 8. 로그인된 정보를 로그아웃하며 로그인이
 * 되기 전의 메뉴로 보여져야 함 9. 패스워드를 확인을 위해 패스워드를 입력받고 로그인된 패스워드와 동일한지 확인후 로그인하였던 회원을 삭제
 * - 받는 값 : 패스워드 확인 0. 종료됨
 */