package com.smile.blog.service;

import com.smile.blog.dao.FriendshipMapper;
import com.smile.blog.domain.Friendship;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-23 17:53
 */
@Service
public class FriendshipService {

    @Resource
    private FriendshipMapper friendshipMapper;
    private final UserService userService;

    public FriendshipService(UserService userService) {
        this.userService = userService;
    }


    @Transactional(rollbackFor = Exception.class)
    public void friendship(Friendship friendship, HttpServletRequest request) {
        friendship.setUid(userService.getNowUser(request).getUid());
        friendship.setTime(LocalDateTime.now());
        friendshipMapper.insert(friendship);
    }
}
