package com.dev3.app.handler;

import com.dev3.app.entity.AbstractMessage;
import com.dev3.app.entity.MessageType;
import com.dev3.app.entity.Suggestion;
import com.dev3.app.entity.TextMessage;
import com.dev3.app.repositoriy.ISuggestionRepository;
import com.dev3.app.repositoriy.ITextMessageRepository;
import com.dev3.app.service.ISuggestionService;
import com.dev3.app.web.WeChatMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by A022713 on 2017/4/21.
 */
@Service(MessageType.MESSAGE_TEXT)
public class TextMessageHandler extends AbsMessageHandler<TextMessage, TextMessage> {
	// @Autowired
	// private TextMessageRepository textMessageRepository;

	@Autowired
	private ISuggestionService suggestionService;

	@Override
	public TextMessage handleMessage(TextMessage message) {
		TextMessage requestMessage = message, responseMessage = null;

		if (requestMessage != null) {

			if (requestMessage.getContent() != null && requestMessage.getContent().startsWith("意见-")) {

				// this.textMessageRepository.save(requestMessage);
				String content = requestMessage.getContent().replaceAll("意见-", "");
				responseMessage = this.getReplyTextMessage(requestMessage);

				Suggestion suggestion = new Suggestion();
				suggestion.setContent(content);
				suggestion.setCreateDate(new Date());
				suggestion.setHasReply(false);
				suggestion.setLikes(0);
				suggestion.setStatus(Suggestion.STATUS_NEW);

				suggestionService.addSuggestion(suggestion);
			}else{
				
			}

			// this.textMessageRepository.save(responseMessage);
		}

		return responseMessage;
	}

	private TextMessage getReplyTextMessage(TextMessage requestMessage) {
		TextMessage replyMessage = new TextMessage();

		replyMessage.setMsgType(MessageType.MESSAGE_TEXT);
		replyMessage.setToUserName(requestMessage.getFromUserName());
		replyMessage.setFromUserName(requestMessage.getToUserName());
		replyMessage.setCreateTime(System.currentTimeMillis());
		replyMessage.setMsgId(UUID.randomUUID().toString());
		replyMessage.setContent("感谢你的宝贵意见，请在周报中查看回复信息，谢谢。");
		replyMessage.setRawMessage(WeChatMessageUtil.textMessageToXml(replyMessage, TextMessage.class));

		return replyMessage;
	}
}
