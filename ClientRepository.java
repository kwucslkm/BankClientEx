package BankClientUp;
//
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class ClientRepository {
	private static ClientRepository repository = new ClientRepository();
	private ClientRepository() {}
	public static ClientRepository getInstance() {
		return repository;
	}
	Scanner sc = new Scanner(System.in);
	Map<String, ClientDTO> cmap = new HashMap<>();
	Map<String, BreakdownDTO> bmap = new HashMap<>();
	UtilDTO utilDTO = new UtilDTO();
	
	public String idDoChk() { // id 중복 체크 메소드
		//
		return utilDTO.idDoChk(cmap);
	}
	public UtilDTO loginDoChk(String id, String password) {
		if (cmap.size() == 0) {
			System.out.println("오류");
		} else {
			for (String c : cmap.keySet()) {
				if (cmap.get(c).getId().equals(id) &&
						cmap.get(c).getPassword().equals(password)) {
					utilDTO.setKeys(c);
					utilDTO.setReturnChk(true);
					return utilDTO;
				}
			}
		}
		return null;
	}
	public boolean save(ClientDTO clientDTO) {
		boolean a = true;
		if (clientDTO != null) {
			cmap.put(clientDTO.getAccount(), clientDTO);
			return a;
		}
		return false;
	}
	public boolean loginCheck(String id, String password) {
		boolean ok = loginDoChk(id, password).isReturnChk();
		return ok;
	}
	public boolean delete(String id, String password) {
		UtilDTO ok = loginDoChk(id, password);
		if (ok.isReturnChk() == true) {
			cmap.remove(ok.getKeys());
			return true;
		}
		return false;
	}
	public String getAccount(String id, String password) {
		UtilDTO ok = loginDoChk(id, password);
		if (ok.isReturnChk() == true) {
			return cmap.get(ok.getKeys()).getAccount();
		}
		return null;
	}
	public ClientDTO findById(String id, String password) {
		UtilDTO ok = loginDoChk(id, password);
		if (ok.isReturnChk() == true) {
			return cmap.get(ok.getKeys());
		}
		return null;
	}
	public boolean update(String id, String password, String updatePassword) {
		UtilDTO ok = loginDoChk(id, password);
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
		for (String b : bmap.keySet()) {
			if (bmap.get(b).getAccount().equals(account)) {
				map.put(bmap.get(b).getDealNo(), bmap.get(b));
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
