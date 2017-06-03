(function() {
    var $ = window.$;
    var backState, forwardState;

    $(document).ready(function() {
        $.ajax({
            url: "./priorities.json",
            dataType: "json"
        })
        .done(function(priorityDescriptions) {
            $(".low-priority")
                .on("mouseover", function() {
                    $(".priority-description").text(formatPriorityDescription(priorityDescriptions["low"]));
                })
                .on("mouseout", function() {
                    $(".priority-description").text("");
                })
                .on("click", function() {
                    $(".priority-boxes > *").removeClass("selected");
                    $(this).addClass("selected");
                });

            $(".med-priority")
                .on("mouseover", function() {
                    $(".priority-description").text(formatPriorityDescription(priorityDescriptions["med"]));
                })
                .on("mouseout", function() {
                    $(".priority-description").text("");
                })
                .on("click", function() {
                    $(".priority-boxes > *").removeClass("selected");
                    $(this).addClass("selected");
                });

            $(".high-priority")
                .on("mouseover", function() {
                    $(".priority-description").text(formatPriorityDescription(priorityDescriptions["high"]));
                })
                .on("mouseout", function() {
                    $(".priority-description").text("");
                })
                .on("click", function() {
                    $(".priority-boxes > *").removeClass("selected");
                    $(this).addClass("selected");
                });

            $(".crit-priority")
                .on("mouseover", function() {
                    $(".priority-description").text(formatPriorityDescription(priorityDescriptions["crit"]));
                })
                .on("mouseout", function() {
                    $(".priority-description").text("");
                })
                .on("click", function() {
                    $(".priority-boxes > *").removeClass("selected");
                    $(this).addClass("selected");
                });

            goState(describeState);
        })
        .fail(function(err) {
            console.log(err);
        });

        $(".go").on("click", function() {
            $.ajax({
                url: "/issues",
                method: "POST",
                headers: {
                    "accept": "application/json",
                    "content-type": "application/json"
                },
                dataType: "json",
                data: JSON.stringify({
                    "title": $(".title").text().trim(),
                    "description": $(".description").text().trim(),
                    "priority": (function() {
                        var selectedClasses = $(".priority-boxes > button.selected").attr("class")
                            .replace("-priority", "")
                            .replace("selected", "")
                            .trim();
                        switch(selectedClasses) {
                            case "low": return "low";
                            case "med": return "medium";
                            case "high": return "high";
                            case "crit": return "critical";
                            default: throw "No priority found";
                        }
                        return "low";
                    }())
                })
            });
        });
    });

    function describeState() {
        $(".main > .title").fadeIn();
        $(".main > .description").fadeIn();
        $(".main > .forward").html("Prioritize &gt;").fadeIn();

        forwardState = prioritizeState;
    }

    function prioritizeState() {
        $(".main > .priority").fadeIn();
        $(".main > .back").html("&lt; Describe").fadeIn();
        $(".main > .go").fadeIn();

        backState = describeState;
    }

    function formatPriorityDescription(description) {
        return description.reduce(function(acc, line) {
            acc += line + "\n";
            return acc;
        }, "");
    }

    function goState(state) {
        $("button.back").off("click");
        $("button.forward").off("click");

        $.when(
            $(".main > *").fadeOut().promise()
        ).then(function() {
            state();

            $("button.back").on("click", function(e) {
                goState(backState);
            });

            $("button.forward").on("click", function(e) {
                goState(forwardState);
            });
        });
    }
}())
