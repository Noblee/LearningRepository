cmake_minimum_required(VERSION 3.17)
project(LearningRepository)

set(CMAKE_CXX_STANDARD 14)

include_directories(cppsrc)

add_executable(LearningRepository
        cppsrc/hello.cpp
        cppsrc/hello.h)
