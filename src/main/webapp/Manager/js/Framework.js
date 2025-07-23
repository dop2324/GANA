// Root Namespace
var Framework = {};

Framework.init	= function()
{
	window.onload = OnLoadCallBack;
	window.onunload	= OnUnLoadCallBack;		
};

// Framework.Delegate Namespace
Framework.Delegate	= {};

Framework.Delegate.createDelegate	= function(delegateInstance, pointingMethod) 
{
	return function() 
	{
		return pointingMethod.apply(delegateInstance, arguments);
	}
}

// Framework.PageLifecycle Namespace
Framework.PageLifecycle	= {};

Framework.PageLifecycle.OnLoadHandler	 = function() 
{
	if (window.Page_Load) window.Page_Load();
}

Framework.PageLifecycle.OnUnLoadHandler	 = function() 
{
	if (window.Page_UnLoad) window.Page_UnLoad();
}

// Framework.DTOFactory Namespace
Framework.DTOFactory	= {};

Framework.DTOFactory.CreateDTO	= function() 
{
	return {
				AddProperty	: function(PropertyName)
				{
					this[PropertyName] = null;
				},

				DeleteProperty : function(PropertyName)
				{
					if(typeof(this[PropertyName]) != "function")
						delete this[PropertyName];
				},

				DeleteAllProperty : function()
				{
					for(var PropertyName in this)
						if(typeof(this[PropertyName]) != "function")
							delete this[PropertyName];
				},

				GetPropertyCount : function()
				{
					var Count = 0;

					for(var PropertyName in this)
						if(typeof(this[PropertyName]) != "function")
							Count++;

					return Count;
				}
	}
}

//Framework.DomPicker Namespace
Framework.DomPicker	= {};

Framework.DomPicker.$	= function(ElementId) 
{
	return document.getElementById(ElementId);
}

Framework.DomPicker.$s	= function(ElementName) 
{
	return document.getElementsByName(ElementName);
}


// Delegate
OnLoadCallBack		= Framework.Delegate.createDelegate(this, Framework.PageLifecycle.OnLoadHandler);
OnUnLoadCallBack	= Framework.Delegate.createDelegate(this, Framework.PageLifecycle.OnUnLoadHandler);

$					= Framework.Delegate.createDelegate(this, Framework.DomPicker.$);
$s					= Framework.Delegate.createDelegate(this, Framework.DomPicker.$s);

Framework.init();




