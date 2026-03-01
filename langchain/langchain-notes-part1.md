# 🔗 LangChain: Fundamentals & Application

LangChain is a powerful framework designed to simplify the creation of applications using large language models (LLMs). This note covers the basics from setup to creating sequential chains and simple web apps.

---

## 🛠️ Environment Setup

### 🏗️ Virtual Environment
Creating a dedicated environment ensures dependency isolation.

```bash
# Create a virtual environment named 'venv'
python -m venv venv

# Activate on Windows
venv\Scripts\activate

# Activate on Mac/Linux
source venv/bin/activate
```

### 📦 Dependencies
Add these to a `requirements.txt` file for easy installation.

```text
langchain
openai
huggingface_hub
python-dotenv
streamlit
ipykernel
```

Install them using:

```bash
# Install all required packages
pip install -r requirements.txt
```

---

## 🔑 Configuration & Security

### 📂 Environment Variables
Never hardcode API keys. Use a `.env` file and `.gitignore` to keep them secure.

```text
# .env file content
OPENAI_API_KEY="your_openai_key"
HUGGINGFACEHUB_API_TOKEN="your_huggingface_token"
```

### 🐍 Python Integration

```python
from dotenv import load_dotenv
import os

# Load variables from .env
load_dotenv()

# Access keys via environment variables
openai_key = os.getenv("OPENAI_API_KEY")
hf_key = os.getenv("HUGGINGFACEHUB_API_TOKEN")
```

---

## 🤖 Working with LLMs

### 🟢 OpenAI Integration

```python
from langchain.llms import OpenAI

# Initialize the LLM with a specific temperature
llm = OpenAI(
    openai_api_key=openai_key,
    temperature=0.6 # Controls creativity (0 = deterministic, 1 = creative)
)

# Predict or generate a response
response = llm.predict("What is the capital of India?")
print(response)
```

### 🌡️ Temperature Setting

| Value | Description | Result |
| :--- | :--- | :--- |
| **0** | Deterministic | Consistent, factual outputs. |
| **0.5 - 0.7** | Balanced | Good for general Q&A. |
| **1.0** | Creative | Diverse and less predictable. |

### 🤗 HuggingFace Integration

```python
from langchain import HuggingFaceHub

# Use open-source models from HuggingFace
llm_hf = HuggingFaceHub(
    repo_id="google/flan-t5-large",
    model_kwargs={"temperature": 0, "max_length": 64}
)

output = llm_hf.predict("Tell me the capital of India")
print(output)
```

---

## ⛓️ Mastering Chains

### 🧬 PromptTemplate + LLMChain
Dynamic prompts allow for flexible user input.

```python
from langchain.prompts import PromptTemplate
from langchain.chains import LLMChain

# Define a dynamic prompt
prompt_template = PromptTemplate(
    input_variables=["country"],
    template="Tell me the capital of {country}"
)

# Bind the LLM and the prompt together
chain = LLMChain(llm=llm, prompt=prompt_template)

# Run the chain
print(chain.run("India"))
```

### 🧱 SimpleSequentialChain
Used when the output of one step is the direct input to the next.

```python
from langchain.chains import SimpleSequentialChain

# Chain 1: Get Capital
capital_template = PromptTemplate(input_variables=["country"], template="Tell me the capital of {country}")
capital_chain = LLMChain(llm=llm, prompt=capital_template)

# Chain 2: Get Places
famous_template = PromptTemplate(input_variables=["capital"], template="Suggest famous places to visit in {capital}")
famous_chain = LLMChain(llm=llm, prompt=famous_template)

# Combine them sequentially
chain = SimpleSequentialChain(chains=[capital_chain, famous_chain])
print(chain.run("India"))
```

---

## 💬 Chat Models & Parsers

### 🗨️ ChatOpenAI
Unlike standard LLMs, Chat models use messages (System, Human, AI) to maintain context.

```python
from langchain.chat_models import ChatOpenAI
from langchain.schema import HumanMessage, SystemMessage

chat_llm = ChatOpenAI(temperature=0.6, model="gpt-3.5-turbo")

# Array of messages defining context and query
response = chat_llm([
    SystemMessage(content="You are a comedian AI assistant"),
    HumanMessage(content="Give me 5 punchlines about AI")
])
print(response.content)
```

### ✂️ Output Parsers
Transform raw string outputs into structured data like lists or JSON.

```python
from langchain.schema import BaseOutputParser

class CommaSeparatedOutput(BaseOutputParser):
    """Parses the output of an LLM call to a list of strings."""
    def parse(self, text: str):
        # Remove whitespace and split by comma
        return text.strip().split(",")

# Chaining with the pipe operator
# chain = chat_prompt | chat_llm | CommaSeparatedOutput()
```

---

## 🌐 Practical Application: Q&A Web App
Build a simple UI using Streamlit to interact with your models.

```python
import streamlit as st
from langchain.llms import OpenAI

def get_openai_response(question):
    # Initialize model inside function for streamlit concurrency
    llm = OpenAI(temperature=0.5)
    return llm.predict(question)

# Streamlit UI Configuration
st.set_page_config(page_title="LangChain Q&A App")
st.header("LangChain Q&A Demo")

user_input = st.text_input("Ask a question:")

if st.button("Submit"):
    response = get_openai_response(user_input)
    st.subheader("Response:")
    st.write(response)
```

---

## 📑 Workflow Summary (Cheat Sheet)

| Task | Key Component |
| :--- | :--- |
| **Logic** | `PromptTemplate` -> `LLMChain` |
| **Pipelines** | `SimpleSequentialChain` or `SequentialChain` |
| **Chat** | `ChatOpenAI` + `HumanMessage` |
| **Parsing** | `BaseOutputParser` |
| **UI** | `streamlit run app.js` |

> [!IMPORTANT]
> Always add `.env` to your `.gitignore` to prevent leaking secret keys to public repositories.

---

## Next Steps 🚀
- Explore **Retrieval Augmented Generation (RAG)**.
- Integrate **Vector Databases** (ChromaDB, Pinecone).
- Use **Memory** to maintain conversation state.
