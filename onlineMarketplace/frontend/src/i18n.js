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
                'Sign Up': 'Kay??t Ol',
                'Password mismatch':'??ifreler uyu??muyor',
                Username:'Kullan??c?? Ad??',
                'username':'kullan??c?? ad??',
                'Name': "??sim",
                Password: '??ifre',
                'Password Repeat': '??ifre Tekrar',
                Login: 'Giri??',
                Logout:'????k????',
                Users:'Kullan??c??lar',
                'Add User':'Kullan??c?? Ekle',
                Next: 'sonraki >',
                Previous: '< ??nceki',
                'Load Failure': 'Liste al??namad??',
                'User Not Found!':'Kullan??c?? Bulunamad??!',
                Edit:'D??zenle',
                Save:'Kaydet',
                Cancel:'??ptal',
                'Change Display Name':'??sminizi De??i??tirin',
                'My Profile':'Profil',
                'There are no products':'??r??n bulunamad??',
                'Load old products':'Eski ??r??n\'leri y??kle',
                'There are new products':'Yeni ??r??n\'ler var',
                'Delete Product':'??r??n\'?? sil',
                'Are you sure to delete this product?':'??r??n\'?? silmek istedi??inizden emin misiniz?',
                'Delete Account':'Hesab?? Sil',
                'Are you sure to delete this account?':'Hesab?? Silmek istedi??inizden emin misiniz?',
                'Products':'??r??nler',
                'Add Product':'??r??n Ekle',
                'Sellers':'Sat??c??lar',
                'Seller':'Sat??c??',
                'Add Seller':'Sat??c?? Ekle',
                "Change Name":"??sim De??i??tir",
                "Change Content":"????eri??i De??i??tir",
                "Change Price":"Fiyat?? De??i??tir",
                'Choose Seller':'Sat??c?? Se??',
                "Change email":'Email\'i De??i??tir',
                'There are no favourite products':'Favori ??r??n yok',
                'Blacklist is empty':'Kara liste bo??',
                'Please choose a seller':'L??tfen bir sat??c?? se??iniz',
                'Price':'Fiyat',
                'Content':'????erik',
                'Seller Not Found!':'Sat??c?? Bulunamad??!',
                'Seller added to blacklist':'Sat??c?? kara listeye eklendi',
                'Delete Seller':'Sat??c??y?? sil',
                'Add to Blacklist':'Kara listeye ekle',
                'Are you sure to delete this seller?':'Bu sat??c??y?? silmek istedi??inizden emin misiniz?',
                'Product Name':'??r??n Ad??',
                'Save User' : 'Kullan??c??y?? Kaydet',
                'User added':'Kullan??c?? eklendi',
                'Seller added':'Sat??c?? kaydedildi',
                'Product added':'??r??n kaydedildi',
                'Go to seller':'Sat??c??ya git',
                'Go to product':'??r??ne git'

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
      ['az ??nce', '??imdi'],
      ['%s saniye ??nce', '%s saniye i??inde'],
      ['1 dakika ??nce', '1 dakika i??inde'],
      ['%s dakika ??nce', '%s dakika i??inde'],
      ['1 saat ??nce', '1 saat i??inde'],
      ['%s saat ??nce', '%s saat i??inde'],
      ['1 g??n ??nce', '1 g??n i??inde'],
      ['%s g??n ??nce', '%s g??n i??inde'],
      ['1 hafta ??nce', '1 hafta i??inde'],
      ['%s hafta ??nce', '%s hafta i??inde'],
      ['1 ay ??nce', '1 ay i??inde'],
      ['%s ay ??nce', '%s ay i??inde'],
      ['1 y??l ??nce', '1 y??l i??inde'],
      ['%s y??l ??nce', '%s y??l i??inde'],
    ][index];
  }
  register('tr',timeagoTR);

export default i18n;