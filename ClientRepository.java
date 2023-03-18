package BankClientUp;
import java.util.ArrayList;
import java.util.Collections;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class ClientRepository {
	private static ClientRepository repository = new ClientRepository();
	private ClientRepository() {
	}
	public static ClientRepository getInstance() {
		return repository;
	}
	Scanner sc = new Scanner(System.in);
	Map<String, ClientDTO> cmap = new HashMap<>();
	Map<String, BreakdownDTO> bmap = new HashMap<>();
	Util util = new Util();
	public String idDoChk() { // id 중복 체크 메소드
		return util.idDoChk(cmap);
	}
	public Util loginDoChk(String id, String password) {
//		Util util = new Util();
		if (cmap.size() == 0) {
			System.out.println("오류");
		} else {
			for (String c : cmap.keySet()) {
				if (cmap.get(c).getId().equals(id) && cmap.get(c).getPassword().equals(password)) {
					util.setKeys(c);
					util.setReturnChk(true);
					return util;
				}
			}
		}
		return null;
	}
	public boolean save(ClientDTO clientDTO) {
		boolean a = true;
		if (clientDTO != null) {
			cmap.put(clientDTO.getAccount(), clientDTO);
			List<String> keys = new ArrayList<>(cmap.keySet());
			Collections.sort(keys);
			for (String s : keys) {
				System.out.print(" 회원 가입 체크");
				System.out.println(cmap.get(s).getAccount());
			}
			return a;
		}
		return false;
	}
	public boolean loginCheck(String id, String password) {
		boolean ok = loginDoChk(id, password).isReturnChk();
		return ok;
	}
	public boolean delete(String id, String password) {
//		Util util = new Util();
		Util ok = loginDoChk(id, password);
		if (ok.isReturnChk() == true) {
			cmap.remove(ok.getKeys());
			return true;
		}
		return false;
	}
	public String getAccount(String id, String password) {
//		Util util = new Util();
		Util ok = loginDoChk(id, password);
		if (ok.isReturnChk() == true) {
			return cmap.get(ok.getKeys()).getAccount();
		}
		return null;
	}
	public ClientDTO findById(String id, String password) {
//		Util util = new Util();
		Util ok = loginDoChk(id, password);
		if (ok.isReturnChk() == true) {
			return cmap.get(ok.getKeys());
		}
		return null;
	}
	public boolean update(String id, String password, String updatePassword) {
//		Util util = new Util();
		Util ok = loginDoChk(id, password);
		if (ok.isReturnChk() == true) {
			cmap.get(ok.getKeys()).setPassword(updatePassword);
			return true;
		}
		return false;
	}
	public Map<String, ClientDTO> findAll() {
		return cmap;
	}
	public Map<String, BreakdownDTO> breakList(String account) {
		Map<String, BreakdownDTO> map = new HashMap<>();
//		List<BreakdownDTO> list = new ArrayList<>();
		for (String b : bmap.keySet()) {
			if (bmap.get(b).getAccount().equals(account)) {
//				System.out.println(bmap.get(b));
//				System.out.println(bmap.get(b).getDealNo());
				map.put(b, bmap.get(b));
			}
		}
		return map;
	}
	public boolean inOut(String account, long money, String inout) {
		for (String c : cmap.keySet()) {
			if (cmap.get(c).getAccount().equals(account)) {
				if (inout.equals("입금")) {
					cmap.get(c).setBalance(cmap.get(c).getBalance() + money);
				} else if (inout.equals("출금") && cmap.get(c).getBalance() >= money) {
					cmap.get(c).setBalance(cmap.get(c).getBalance() - money);
				} else {
					System.out.println("잔액부족");
					return false;
				}
				BreakdownDTO breakdownDTO = new BreakdownDTO();
				breakdownDTO.setAccount(account);
				breakdownDTO.setDivision(inout);
				breakdownDTO.setDealMoney(money);
				breakdownDTO.setTotalMoney(cmap.get(c).getBalance());
				bmap.put(breakdownDTO.getDealNo(), breakdownDTO);
				return true;
			}
		}
		return false;
	}
	public boolean transferCheck(String transferAccount) {
		for (String c : cmap.keySet()) {
			if (cmap.get(c).getAccount().equals(transferAccount)) {
				return true;
			}
		}
		return false;
	}
}
