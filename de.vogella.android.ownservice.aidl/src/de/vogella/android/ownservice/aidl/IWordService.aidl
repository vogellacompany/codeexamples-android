package de.vogella.android.ownservice.aidl;

import de.vogella.android.ownservice.aidl.MyMessage;

interface IWordService {
	List<MyMessage> getWords();
}