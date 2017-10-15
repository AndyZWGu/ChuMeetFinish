            $(document).ready(function(){
                $('#image-4').rcrop({
                    minSize : [150,100],
                    preserveAspectRatio : true,
                    grid : false,
                    
                    preview : {
                        display: true,
                        size : [150,100],
                    }
                });
            });
