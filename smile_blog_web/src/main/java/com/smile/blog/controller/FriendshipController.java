package com.smile.blog.controller;

import com.smile.blog.domain.Friendship;
import com.smile.blog.service.FriendshipService;
import com.smile.blog.vo.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-23 17:52
 */

@RestController
@RequestMapping("friendship")
@Api(tags = "friendship")
public class FriendshipController {

    private final FriendshipService friendshipService;

    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @PostMapping
    @ApiOperation("添加友链")
    public BaseResult addFriendShip(@RequestBody @NotNull Friendship friendship, HttpServletRequest request) {
        friendshipService.friendship(friendship, request);
        return BaseResult.success();
    }
}
