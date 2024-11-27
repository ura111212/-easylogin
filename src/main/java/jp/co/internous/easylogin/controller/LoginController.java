package jp.co.internous.easylogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.easylogin.model.domain.MstUser;
import jp.co.internous.easylogin.model.mapper.MstUserMapper;

@Controller //Controllerはフロントエンドからリクエストを受け取り、バックエンドとの橋渡しを担う。
/* @ControllerまたはRestControllerを入力することでSpring Bootはそのクラスを
 * Controller として認識する。
 */
@RequestMapping("/easylogin")
/*コントローラに付与して、リクエスト URL に対応付けし
*どのメソッドが処理を実行するか定義するアノテーション。
*ブラウザでurlにアクセスすることで、コントローラを探し、マッピングする。
*/
public class LoginController {
	
	@Autowired
	//Autowiredアノテーションを付与して宣言されたオブジェクトはSpring Bootによって自動的にインスタンス化される
	private MstUserMapper userMapper;
	
	@GetMapping("/") //@GetMappingはデータの取得(Postは登録、Putは更新、Deleteは削除)
	public String index() {
		return "index"; //index.htmlに遷移させる
	}
	
	@GetMapping("login")
	public String login(String userName, String password, Model model) {
		/*Modelとは、SpringBoot側で作成されているインターフェース
		 * HTML内に書かれたthymeleafテンプレートに対して
		 * データを受け渡す入れ物として使用される。
		 */
		
		MstUser user = userMapper.findByUserNameAndPassword(userName, password);
		//　mapperを使ってデータベースにアクセスする
		
		if (user == null) {
			model .addAttribute("message", "ゲストさん、ようこそ！");
		} else {
			model .addAttribute("message", user.getFullName() + "さん、ようこそ！");
		}
		
		return "login";
		// login.htmlに遷移させる
	}

}
