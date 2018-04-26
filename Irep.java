
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author ito
 *
 */
public class Irep {
	public static void main(String[] args) {
		
		String folderPath;	//検索を行うフォルダのパスの変数
		String chara;	//検索文字の変数
		
		//フォルダのパスを取得を行うメソッド
		folderPath = getPath();
		
		//取得したパスが存在するかを確認するメソッド
		checkPath(folderPath);
		
		//検索を行う文字列を取得するメソッド
		chara = serChara();
		
		//フォルダ内のファイルを配列にして取得
		File file = new File(folderPath);
		String files[] = file.list();
		//ファイルの読み込みを行う
    	 for (int i=0; i<files.length; i++) {
			 //ファイル名の取得
			String fileName = files[i]; 
			
			//拡張子の判定
			if(fileName.endsWith(".txt")) {
				fileRead(folderPath,fileName,chara);
			}
		 }
   		System.out.println("処理を終了します。");
	}
	
	/** 
	 * ・パスの取得を行うメソッド
	 *   戻り値
	 *   folderPath;検索を行うフォルダのパス
	 * 
	 */
	public static String getPath() {
		System.out.println("読み込むパスを入力してください");
		//コマンドラインからパスを取得
		Scanner scPath = new Scanner(System.in);
		String folderPath = scPath.nextLine();
		return folderPath;
	}
	
	/**
	 * ・検索を行う文字列を取得するメソッド
	 *   戻り値
	 *   chara;検索を行う文字列
	 * 
	 */
	public static String serChara() {
		//コマンドラインから検索文字列の取得
		System.out.println("検索する単語を入力してください");
		Scanner scChara = new Scanner(System.in);
		String chara = scChara.nextLine();
		return chara;
	}
	
	/**
	 *・取得したフォルダの存在判定を行うメソッド
	 *  引数
	 *  folderPath;取得したフォルダのパス
	 * 
	 */
	public static void checkPath(String folderPath) {
		
		File path = new File(folderPath);
		//パスの存在判定を行う
		if(path.exists() == false) {
			System.out.println("パスが存在しませんでした。");
			System.exit(0);
		}
	}

	/**
	 * ファイルを読み込み書き出すメソッド
	 * 引数
	 * folderPath;フォルダのパス
	 * fileName;ファイル名
	 * chara;検索を行う文字列
	 */
	public static void fileRead(String folderPath, String fileName, String chara) {
		
		int row = 0;//行数のカウント変数
		FileReader fr =null;
		BufferedReader br =null;
		
		 try { 
			fr = new FileReader(folderPath + "\\" + fileName);
			br = new BufferedReader(fr);
			String line;
			
			//最終行まで繰り返す処理
			while((line = br.readLine()) !=null) {
				//行数のカウント
				row = row + 1;

				//検索する文字列の判定
				if(line.contains(chara)) {
					System.out.println(folderPath + "\\" + fileName + ";" + row +"行目");
				}
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
				fr.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}	