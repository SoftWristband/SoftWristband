package com.softwristband.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.softwristband.softwristband.R;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;


public class ArticleFragment extends Fragment {

    private View view;
//    private ListView taskList;
//    private TextView textView;
//    public static List<TaskListItem> mList = new ArrayList<>();
//    public static int CurrentCheck=0;
//    private TaskAdapter taskAdapter=new TaskAdapter();
//    private PtrClassicFrameLayout ptrFrame;
//    private PlanTask planTask;
//    private String planResponse;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_task, container, false);
       // init();
        return view;
    }
//    private void init()
//    {
//        taskList=(ListView)view.findViewById(R.id.task_list);
//        textView=(TextView)view.findViewById(R.id.text);
//        taskList.setAdapter(taskAdapter);
//        ptrFrame=(PtrClassicFrameLayout)view.findViewById(R.id.ptr_frame);
//        ptrFrame.setPtrHandler(new PtrHandler() {
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                frame.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        getTask();
//                    }
//                },0);
//            }
//            @Override
//            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
//            }
//        });
//        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                CurrentCheck=i;
//                System.out.println("CurrentCheck"+i);
//                Intent intent = new Intent(MyApplication.getContext(),DataActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//    private void getTask()
//    {
//        final Handler handler = new Handler()
//        {
//            public void handleMessage(Message msg)
//            {
//                switch (msg.what) {
//                    case 1:
//                        ptrFrame.refreshComplete();
//                        Toast.makeText(MyApplication.getContext(),"数据加载完成",Toast.LENGTH_SHORT).show();
//                        getTaskList();
//                        break;
//                    case 2:
//                        ptrFrame.refreshComplete();
//                        Toast.makeText(MyApplication.getContext(),"数据加载失败",Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//        };
//        String address = Constant.BASE_URL+"Plan?Type=Get";
//        //String address = Constant.BASE_URL+"j_security_check?j_username=12345678&j_password=1234";
//        String data = "{\"inspector_id\":\"12345678\"}";
//        if(Utils.isOpenNetwork(MyApplication.getContext())==false)
//        {
//            ptrFrame.refreshComplete();
//            Toast.makeText(MyApplication.getContext(),"请检查网络链接情况",Toast.LENGTH_SHORT).show();
//        }
//        HttpUtil.sendHttpPostRequest(address,data, new HttpCallbackListener()
//        {
//            @Override
//            public void onFinish(String response)
//            {
//                if(response.equals("Post Response: Failed"))
//                {
//                    Message message = new Message();
//                    message.what=2;
//                    handler.sendMessage(message);
//                }
//                else
//                {
//                    Message message = new Message();
//                    message.what = 1;
//                    Gson gson = new Gson();
//
//                    planTask=gson.fromJson(response,PlanTask.class);
//                    planResponse=response;
//                    System.out.println("Task"+response);
//                    handler.sendMessage(message);
//                }
//
//            }
//            @Override
//            public void onError(Exception e)
//            {
//                Message message = new Message();
//                message.what=2;
//                handler.sendMessage(message);
//                System.out.println(e);
//            }
//        });
//    }
//    private void getTaskList()
//    {
//        if(planTask!=null)
//        {
//            mList.clear();
//            if(planTask!=null)
//            for(int i=0;i<planTask.getPlanList().size();i++)
//            {
//                for(int j=0;j<planTask.getPlanList().get(i).getTasklist().size();j++)
//                {
//                    TaskListItem taskListItem = new TaskListItem();
//                    taskListItem.setPlanName(planTask.getPlanList().get(i).getPlan_name());
//                    taskListItem.setStartTime(planTask.getPlanList().get(i).getPlan_start_time());
//                    taskListItem.setEqName(planTask.getPlanList().get(i).getTasklist().get(j).getEq_name());
//                    taskListItem.setCheckListBeen(planTask.getPlanList().get(i).getTasklist().get(j).getCheckList());
//                    mList.add(taskListItem);
//                }
//            }
//        }
//
//        taskAdapter.notifyDataSetChanged();
//    }
//    class TaskAdapter extends BaseAdapter
//    {
//
//        @Override
//        public int getCount() {
//            return mList.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return i;
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return i;
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent)
//        {
//            ViewHolder holder;
//            if(convertView==null)
//            {
//                convertView = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.task_item,parent,false);
//                holder=new ViewHolder();
//                holder.tvEqName=(TextView)convertView.findViewById(R.id.equipment_name);
//                holder.tvStartTime=(TextView)convertView.findViewById(R.id.time);
//                holder.tvPlanName=(TextView)convertView.findViewById(R.id.plan_name);
//                convertView.setTag(holder);
//            }
//            else
//            {
//                holder=(ViewHolder) convertView.getTag();
//            }
//            holder.tvEqName.setText(mList.get(position).getEqName());
//            holder.tvStartTime.setText(mList.get(position).getStartTime());
//            holder.tvPlanName.setText(mList.get(position).getPlanName());
//            return convertView;
//        }
//        class ViewHolder
//        {
//            TextView tvEqName;
//            TextView tvStartTime;
//            TextView tvPlanName;
//        }
//    }
}
