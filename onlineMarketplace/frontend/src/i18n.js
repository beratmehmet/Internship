import i18n from 'i18next';
import {initReactI18next} from 'react-i18next';
import {register} from 'timeago.js';

i18n.use(initReactI18next).init({
    resources: {
        en:{
            translations:{
                'Sign Up': 'Sign Up',
                'Password mismatch':'Password mismatch',
                Username:'Username',
                'username':'username',
                'Name': "Name",
                Password: 'Password',
                'Password Repeat': 'Password Repeat',
                Login: 'Login',
                Logout:'Logout',
                Users:'Users',
                'Add User':'Add User',
                Next: 'next >',
                Previous: '< previous',
                'Load Failure':'Load Failure',
                'User Not Found!':'User Not Found!',
                Edit:'Edit',
                Save:'Save',
                Cancel:'Cancel',
                'Change Display Name':'Change Display Name',
                'My Profile':'My Profile',
                'There are no products':'There are no products',
                'Load old products':'Load old products',
                'There are new products':'There are new products',
                'Delete Product':'Delete Product',
                'Are you sure to delete this product?':'Are you sure to delete this product?',
                'Delete Account':'Delete Account',
                'Are you sure to delete your account?':'Are you sure to delete your account?',
                'Products':'Products',
                'Add Product':'Add Product',
                'Sellers':'Sellers',
                'Seller':'Seller',
                'Add Seller':'Add Seller',
                "Change Name":"Change Name",
                "Change Content":"Change Content",
                "Change Full Name":"Change Full Name",
                'Choose Seller':'Choose Seller',
                "Change email":"Change email",
                'There are no favourite products':'There are no favourite products',
                'Blacklist is empty':'Blacklist is empty',
                'Please choose a seller':'Please choose a seller',
                'Price':'Price',
                'Content':'Content',
                'Seller Not Found!':'Seller Not Found!',
                'Seller added to blacklist':'Seller added to blacklist',
                'Delete Seller':'Delete Seller',
                'Add to Blacklist':'Add to Blacklist',
                'Are you sure to delete this seller?':'Are you sure to delete this seller?',
                'Product Name':'Product Name',
                'Save User' : 'Save User',
                'User added':'User added',
                'Seller added':'Seller added',
                'Product added':'Product added',
                'Go to seller':'Go to seller',
                'Go to product':'Go to product'



            }
        },
    

        tr:{
            translations:  {
                'Sign Up': 'Kayıt Ol',
                'Password mismatch':'Şifreler uyuşmuyor',
                Username:'Kullanıcı Adı',
                'username':'kullanıcı adı',
                'Name': "İsim",
                Password: 'Şifre',
                'Password Repeat': 'Şifre Tekrar',
                Login: 'Giriş',
                Logout:'Çıkış',
                Users:'Kullanıcılar',
                'Add User':'Kullanıcı Ekle',
                Next: 'sonraki >',
                Previous: '< önceki',
                'Load Failure': 'Liste alınamadı',
                'User Not Found!':'Kullanıcı Bulunamadı!',
                Edit:'Düzenle',
                Save:'Kaydet',
                Cancel:'İptal',
                'Change Display Name':'İsminizi Değiştirin',
                'My Profile':'Profil',
                'There are no products':'Ürün bulunamadı',
                'Load old products':'Eski ürün\'leri yükle',
                'There are new products':'Yeni ürün\'ler var',
                'Delete Product':'Ürün\'ü sil',
                'Are you sure to delete this product?':'Ürün\'ü silmek istediğinizden emin misiniz?',
                'Delete Account':'Hesabı Sil',
                'Are you sure to delete this account?':'Hesabı Silmek istediğinizden emin misiniz?',
                'Products':'Ürünler',
                'Add Product':'Ürün Ekle',
                'Sellers':'Satıcılar',
                'Seller':'Satıcı',
                'Add Seller':'Satıcı Ekle',
                "Change Name":"İsim Değiştir",
                "Change Content":"İçeriği Değiştir",
                "Change Price":"Fiyatı Değiştir",
                'Choose Seller':'Satıcı Seç',
                "Change email":'Email\'i Değiştir',
                'There are no favourite products':'Favori ürün yok',
                'Blacklist is empty':'Kara liste boş',
                'Please choose a seller':'Lütfen bir satıcı seçiniz',
                'Price':'Fiyat',
                'Content':'İçerik',
                'Seller Not Found!':'Satıcı Bulunamadı!',
                'Seller added to blacklist':'Satıcı kara listeye eklendi',
                'Delete Seller':'Satıcıyı sil',
                'Add to Blacklist':'Kara listeye ekle',
                'Are you sure to delete this seller?':'Bu satıcıyı silmek istediğinizden emin misiniz?',
                'Product Name':'Ürün Adı',
                'Save User' : 'Kullanıcıyı Kaydet',
                'User added':'Kullanıcı eklendi',
                'Seller added':'Satıcı kaydedildi',
                'Product added':'Ürün kaydedildi',
                'Go to seller':'Satıcıya git',
                'Go to product':'Ürüne git'

            }
        }
    },
    fallbackLng: 'tr',
    ns:['translations'],
    defaultNS: 'translations',
    keySeparator: false,
    interpolation: {
        escapeValue:false,
        formatSeparator: ','
    },
    react:{
        useSuspense:true
    }
});

const timeagoTR =(number, index)=> {
    return [
      ['az önce', 'şimdi'],
      ['%s saniye önce', '%s saniye içinde'],
      ['1 dakika önce', '1 dakika içinde'],
      ['%s dakika önce', '%s dakika içinde'],
      ['1 saat önce', '1 saat içinde'],
      ['%s saat önce', '%s saat içinde'],
      ['1 gün önce', '1 gün içinde'],
      ['%s gün önce', '%s gün içinde'],
      ['1 hafta önce', '1 hafta içinde'],
      ['%s hafta önce', '%s hafta içinde'],
      ['1 ay önce', '1 ay içinde'],
      ['%s ay önce', '%s ay içinde'],
      ['1 yıl önce', '1 yıl içinde'],
      ['%s yıl önce', '%s yıl içinde'],
    ][index];
  }
  register('tr',timeagoTR);

export default i18n;