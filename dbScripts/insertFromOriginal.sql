insert into song(id, artist, title, surname, name_index, name_length, date_inserted, date_modified, user_inserted, user_modified)
select sOld.id, sOld.artist, sold.title, sold.surname, sold.nameIndex, sold.nameLength, sold.date_ins, sold.date_mod, sold.user_ins, sold.user_mod from songs sOld;