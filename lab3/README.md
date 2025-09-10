# Performance Testing Report

## 1. Туршилтын тохиргоо
- API: [Binance BTC/USDT Price](https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT)  
- Tool: Postman 
- Iterations: 100  
- Test script: Checks response time, status, structure, symbol, and price 

---

## 2. Үр дүн
- **Total run duration**: 18.5 секунд  
- **Total data received**: 4.6 KB  
- **Average response time**: 165 ms  
- **Minimum response time**: 135 ms  
- **Maximum response time**: 705 ms  
- **Failures**: 1 (response time > 500ms)  

---

## 3. Дүн шинжилгээ
- Дундаж response time нь 165ms → маш сайн үзүүлэлт.  
- Ихэнх хүсэлтүүд хурдан хариу өгсөн (100 хүсэлтийн 99 нь < 500ms).  
- 1 хүсэлт (705ms) нь тестийн нөхцөл зөрчсөн → энэ нь түр зуурын сүлжээний саатал эсвэл серверийн ачаалалтай холбоотой байж болно.  
- Хүлээн авсан өгөгдлийн хэмжээ бага (4.6 KB) учир серверийн талаас ачаалал их биш байна.  

---

## 4. Дүгнэлт
- Binance API нь тогтвортой, ихэнхдээ <200ms дотор хариу өгч байна.  
- Ганцхан алдаа гарсан нь сүлжээний саатал эсвэл серверийн гэнэтийн хариу удаашрал байж болох ч нийт гүйцэтгэлд ноцтой нөлөө үзүүлээгүй.  
- Энэ API-г реаль-тайм дата татахад ашиглахад тохиромжтой.  